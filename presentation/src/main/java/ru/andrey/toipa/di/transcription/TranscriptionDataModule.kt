package ru.andrey.toipa.di.transcription

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.andrey.data.db.TranscriptionDatabase
import ru.andrey.toipa.di.scope.Feature

@Module
class TranscriptionDataModule {

    @Feature
    @Provides
    fun provideRoomDatabase(context: Context): TranscriptionDatabase {
        return Room.databaseBuilder(context, TranscriptionDatabase::class.java, "transcription.db")
            .build()
    }
}