package ru.andrey.toipa.di.transcription

import dagger.Module
import dagger.Provides
import ru.andrey.data.db.TranscriptionDatabase
import ru.andrey.data.repository.TranscriptionRepositoryImpl
import ru.andrey.data.repository.dictionary.DatabaseDictionaryService
import ru.andrey.data.repository.dictionary.DictionaryService
import ru.andrey.domain.interactor.TranscriptionInteractor
import ru.andrey.domain.repository.TranscriptionRepository
import ru.andrey.toipa.di.scope.Feature

@Module
class TranscriptionModule {

    @Feature
    @Provides
    fun provideTranscriptionInteractor(repository: TranscriptionRepository): TranscriptionInteractor {
        return TranscriptionInteractor(repository)
    }

    @Feature
    @Provides
    fun provideTranscriptionRepository(dictionaryService: DictionaryService): TranscriptionRepository {
        return TranscriptionRepositoryImpl(dictionaryService)
    }

    @Feature
    @Provides
    fun provideDictionaryService(roomDatabase: TranscriptionDatabase): DictionaryService {
        return DatabaseDictionaryService(roomDatabase.transcriptionDao())
    }
}