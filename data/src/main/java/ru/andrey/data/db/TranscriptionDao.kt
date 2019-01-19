package ru.andrey.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import ru.andrey.data.db.entity.TranscriptionData

@Dao
interface TranscriptionDao {

    @Query("SELECT * FROM transcriptions")
    fun getAll(): List<TranscriptionData>

    @Query("SELECT * FROM transcriptions WHERE word = :word")
    fun findByWord(word: String): TranscriptionData?
}
