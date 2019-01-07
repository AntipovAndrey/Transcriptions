package ru.andrey.toipa.ui

import ru.andrey.domain.model.Transcription

sealed class TranscriptionsState(
    val result: Transcription? = null,
    val error: Boolean = false,
    val loading: Boolean = false
)

object Initial : TranscriptionsState()

object Loading : TranscriptionsState(loading = true)

object Error : TranscriptionsState(error = true)

class Success(word: Transcription) : TranscriptionsState(word)
