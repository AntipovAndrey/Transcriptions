package ru.andrey.data.repository

import ru.andrey.domain.model.Transcription
import ru.andrey.domain.repository.TranscriptionRepository

class TranscriptionRepositoryImpl : TranscriptionRepository {

    override fun getTranscription(word: String): Transcription {
        return Transcription(word, listOf("[$word]", "{$word}"))
    }
}
