package ru.andrey.data.repository.dictionary

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import ru.andrey.domain.model.Variant
import java.net.URLEncoder.encode

class CollinsHtmlDictionaryService(private val okHttpClient: OkHttpClient) : DictionaryService {

    // some of the transcriptions are marked with a hint
    private val ipaHints = listOf("unstressed", "weak", "STRONG").joinToString("|")
    private val hintsRegex = Regex("\\s*\\b($ipaHints)\\b\\s*")

    override fun getTranscriptions(word: String): Map<Variant, List<String>> {
        val request = Request.Builder()
            .url(url(word)).build()

        val html = okHttpClient.newCall(request)
            .execute()
            .body()
            ?.string() ?: throw NoSuchElementException(url(word))

        val dictionaries = Jsoup.parse(html).select("div.dictentry")

        return dictionaries.asSequence()
            .groupBy(::variant)
            .toMap()
            .mapValues { (_, dictentries) -> extractTranscriptions(dictentries) }
    }

    private fun extractTranscriptions(dictentries: List<Element>): List<String> {
        return dictentries.asSequence()
            .map { it.select(".pron") }
            .map { it.text() }
            .map { it.split(';', ',', ' ', '(', ')') }
            .flatMap { it.asSequence() }
            .map(removeHints())
            .map { it.trim() }
            .distinct()
            .filter { it.isNotEmpty() }
            .toList()
    }

    private fun removeHints(): (String) -> String = {
        it.replace(hintsRegex, "")
    }

    private fun variant(dictentry: Element): Variant {
        val name = dictentry.select(".dictname").text()
        return when {
            name.contains("ritish") -> Variant.BRITISH
            name.contains("merican") -> Variant.AMERICAN
            else -> Variant.UNKNOWN
        }
    }

    private fun url(word: String) = "https://www.collinsdictionary.com/dictionary/english/${encode(word, "UTF-8")}"
}