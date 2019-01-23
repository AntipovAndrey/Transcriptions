package ru.andrey.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import ru.andrey.data.db.entity.TranscriptionData

@Dao
interface TranscriptionDao {

    @Query("SELECT * FROM transcriptions WHERE word=:word AND variant=:variant")
    fun findByWord(word: String, variant: Int): TranscriptionData?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(data: TranscriptionData)
}