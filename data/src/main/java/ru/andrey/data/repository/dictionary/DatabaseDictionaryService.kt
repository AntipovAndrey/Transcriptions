package ru.andrey.data.repository.dictionary

import ru.andrey.data.db.TranscriptionDao
import ru.andrey.domain.model.Variant

class DatabaseDictionaryService(private val dao: TranscriptionDao) : DictionaryService {

    override fun getTranscriptions(word: String): Map<Variant, List<String>> {
        return mapOf(
            Variant.AMERICAN to (dao.findByWordAmerican(word).map { it.ipa.toString() }),
            Variant.BRITISH to (dao.findByWordBritish(word).map { it.ipa.toString() })
        )
    }
}
