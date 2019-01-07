package ru.andrey.toipa.di.transcription

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.andrey.data.repository.TranscriptionRepositoryImpl
import ru.andrey.data.repository.dictionary.CollinsHtmlDictionaryService
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
    fun provideDictionaryService(okHttpClient: OkHttpClient): DictionaryService {
        return CollinsHtmlDictionaryService(okHttpClient)
    }
}