package ru.andrey.toipa.di.transcription

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import ru.andrey.data.db.TranscriptionDao
import ru.andrey.data.db.TranscriptionDatabase
import ru.andrey.toipa.di.scope.Feature

@Module
class TranscriptionDataModule {

    @Feature
    @Provides
    fun provideRoomDatabase(application: Application): TranscriptionDatabase {
        return Room.databaseBuilder(
            application,
            TranscriptionDatabase::class.java,
            "transcriptions_cache"
        ).build()
    }

    @Feature
    @Provides
    fun provideTranscriptionDao(transcriptionDatabase: TranscriptionDatabase): TranscriptionDao {
        return transcriptionDatabase.transcriptionDao()
    }
}