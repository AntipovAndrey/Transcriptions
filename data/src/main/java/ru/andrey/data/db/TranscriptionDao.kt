package ru.andrey.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import ru.andrey.data.db.entity.TranscriptionData
import ru.andrey.data.db.entity.TranscriptionUsData

@Dao
interface TranscriptionDao {

    @Query("SELECT * FROM transcriptions WHERE word = :word")
    fun findByWordBritish(word: String): List<TranscriptionData>

    @Query("SELECT * FROM transcriptions_us WHERE word = :word")
    fun findByWordAmerican(word: String): List<TranscriptionUsData>
}
