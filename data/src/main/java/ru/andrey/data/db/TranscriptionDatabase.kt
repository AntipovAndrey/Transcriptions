package ru.andrey.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.andrey.data.db.entity.IpaData
import ru.andrey.data.db.entity.TranscriptionData

@Database(
    entities = [TranscriptionData::class, IpaData::class],
    version = 1,
    exportSchema = false
)
abstract class TranscriptionDatabase : RoomDatabase() {

    abstract fun transcriptionDao(): TranscriptionDao
}
