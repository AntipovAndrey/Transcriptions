package ru.andrey.domain.interactor

import ru.andrey.domain.model.Transcription
import ru.andrey.domain.repository.TranscriptionRepository

class TranscriptionInteractor constructor(private val repository: TranscriptionRepository) {

    fun transcriptionFor(word: String): Transcription = repository.getTranscription(word)
}