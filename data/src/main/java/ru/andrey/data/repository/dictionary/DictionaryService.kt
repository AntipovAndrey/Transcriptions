package ru.andrey.data.repository.dictionary

interface DictionaryService {

    fun getTranscriptions(word: String): List<String>
}