package ru.andrey.data.repository

import ru.andrey.data.db.TranscriptionDao
import ru.andrey.data.db.entity.IpaData
import ru.andrey.data.db.entity.TranscriptionData
import ru.andrey.data.db.entity.TranscriptionWithIpas
import ru.andrey.data.repository.dictionary.DictionaryService
import ru.andrey.domain.model.Transcription
import ru.andrey.domain.model.Variant.AMERICAN
import ru.andrey.domain.model.Variant.BRITISH
import ru.andrey.domain.repository.TranscriptionRepository

class TranscriptionRepositoryImpl(
    private val dictionaryService: DictionaryService,
    private val dbCache: TranscriptionDao
) : TranscriptionRepository {

    override fun getTranscription(word: String): Transcription {
        val americanCached = dbCache.findByWord(word, AMERICAN_CODE)
        val britishCached = dbCache.findByWord(word, BRITISH_CODE)

        val americanIpas: List<String>
        val britishIpas: List<String>

        if (americanCached != null && britishCached != null) {
            americanIpas = processCache(americanCached)
            britishIpas = processCache(britishCached)
        } else {
            val transcriptions = dictionaryService.getTranscriptions(word)
            americanIpas = (transcriptions[AMERICAN] ?: emptyList())
            britishIpas = (transcriptions[BRITISH] ?: emptyList())
            saveToCache(word, americanIpas, britishIpas)
        }

        return Transcription(word, mapOf(AMERICAN to americanIpas, BRITISH to britishIpas))
    }

    private fun processCache(cached: TranscriptionWithIpas) = cached.ipas.sortedBy { it.order }.map { it.ipa }

    private fun saveToCache(word: String, americanIpas: List<String>, britishIpas: List<String>) {
        saveToCache(word, americanIpas, AMERICAN_CODE)
        saveToCache(word, britishIpas, BRITISH_CODE)
    }

    private fun saveToCache(word: String, ipaList: List<String>, code: Int) {
        TranscriptionWithIpas().apply {
            transcriptionData = TranscriptionData(word = word, variant = code)
            ipas = ipaList.mapIndexed { index, ipa -> IpaData(ipa = ipa, order = index) }
        }.also { dbCache.insert(it) }
    }

    companion object {
        private const val AMERICAN_CODE = 0
        private const val BRITISH_CODE = 1
    }
}
