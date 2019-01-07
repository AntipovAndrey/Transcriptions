package ru.andrey.data.repository.dictionary

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.net.URLEncoder.encode

class CollinsHtmlDictionaryService(private val okHttpClient: OkHttpClient) : DictionaryService {

    override fun getTranscriptions(word: String): List<String> {
        val request = Request.Builder()
            .url(url(word)).build()

        val html = okHttpClient.newCall(request)
            .execute()
            .body()
            ?.string() ?: throw NoSuchElementException()

        val document = Jsoup.parse(html)
        val dictionaries = document.select("div.dictentry .pron")

        return dictionaries.asSequence()
            .map { it.text() }
            .map { it.split(";") }
            .flatMap { it.asSequence() }
            .map { it.trim() }
            .distinct()
            .filter { it.isNotEmpty() }
            .toList()
    }

    private fun url(word: String) = "https://www.collinsdictionary.com/dictionary/english/${encode(word, "UTF-8")}"
}