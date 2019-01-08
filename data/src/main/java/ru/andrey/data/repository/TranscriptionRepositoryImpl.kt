package ru.andrey.data.repository

import ru.andrey.data.repository.dictionary.DictionaryService
import ru.andrey.domain.model.Transcription
import ru.andrey.domain.model.Variant.AMERICAN
import ru.andrey.domain.model.Variant.BRITISH
import ru.andrey.domain.repository.TranscriptionRepository

class TranscriptionRepositoryImpl(private val dictionaryService: DictionaryService) : TranscriptionRepository {

    override fun getTranscription(word: String): Transcription {
        val transcriptions = dictionaryService.getTranscriptions(word)
        return Transcription(
            word, mapOf(
                AMERICAN to (transcriptions[AMERICAN] ?: emptyList()),
                BRITISH to (transcriptions[BRITISH] ?: emptyList())
            )
        )
    }
}
