package ru.andrey.data.db

import android.arch.persistence.room.*
import ru.andrey.data.db.entity.TranscriptionData
import ru.andrey.data.db.entity.TranscriptionWithIpas

@Dao
interface TranscriptionDao {

    @Transaction
    @Query("SELECT * FROM transcriptions WHERE word=:word AND variant=:variant")
    fun findByWord(word: String, variant: Int): TranscriptionWithIpas?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(data: TranscriptionData)
}
