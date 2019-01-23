package ru.andrey.data.db

import android.arch.persistence.room.*
import ru.andrey.data.db.entity.IpaData
import ru.andrey.data.db.entity.TranscriptionData
import ru.andrey.data.db.entity.TranscriptionWithIpas

@Dao
interface TranscriptionDao {

    @Transaction
    @Query("SELECT * FROM transcriptions WHERE word=:word AND variant=:variant")
    fun findByWord(word: String, variant: Int): TranscriptionWithIpas?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: TranscriptionData): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: IpaData)

    @Transaction
    fun insert(data: TranscriptionWithIpas) {
        val longId = insert(data.transcriptionData)
        data.ipas.forEach {
            it.transcriptionId = longId.toInt()
            insert(it)
        }
    }
}
