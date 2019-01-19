package ru.andrey.data.repository.dictionary

import ru.andrey.domain.model.Variant

interface DictionaryService {

    fun getTranscriptions(word: String): Map<Variant, List<String>>
}
