package ru.andrey.toipa.ui

import ru.andrey.domain.model.Transcription
import ru.andrey.toipa.utils.Event
import ru.andrey.toipa.utils.toEvent

sealed class TranscriptionsState(
    val result: List<Transcription> = emptyList(),
    val error: Event<Boolean> = false.toEvent(),
    val loading: Boolean = false
)

object Initial : TranscriptionsState()

object Loading : TranscriptionsState(loading = true)

class Error : TranscriptionsState(error = true.toEvent())

class Success(words: List<Transcription>) : TranscriptionsState(words)
