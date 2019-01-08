package ru.andrey.domain.model

class Transcription(
    val word: String,
    val transcriptions: Map<Variant, List<String>>
)
