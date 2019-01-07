package ru.andrey.domain.repository

import ru.andrey.domain.model.Transcription

interface TranscriptionRepository {

    fun getTranscription(word: String): Transcription
}