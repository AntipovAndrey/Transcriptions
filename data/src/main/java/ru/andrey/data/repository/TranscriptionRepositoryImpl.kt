package ru.andrey.data.repository

import ru.andrey.data.repository.dictionary.DictionaryService
import ru.andrey.domain.model.Transcription
import ru.andrey.domain.repository.TranscriptionRepository

class TranscriptionRepositoryImpl(private val dictionaryService: DictionaryService) : TranscriptionRepository {

    override fun getTranscription(word: String): Transcription {
        return Transcription(word, dictionaryService.getTranscriptions(word))
    }
}
