package ru.andrey.toipa.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.andrey.domain.interactor.TranscriptionInteractor
import ru.andrey.toipa.utils.ifActive
import javax.inject.Inject

class TranscriptionViewModel @Inject constructor(private val interactor: TranscriptionInteractor) : ViewModel() {

    private val transcriptions: MutableLiveData<TranscriptionsState> = MutableLiveData()

    private var fetchIpaJob: Job? = null

    init {
        transcriptions.value = Initial
    }

    fun observeTranscriptions(): LiveData<TranscriptionsState> = transcriptions

    fun setSentence(sentence: String) {
        fetchIpaJob?.cancel()
        if (sentence.isEmpty()) {
            transcriptions.postValue(Initial)
            return
        }
        fetchIpaJob = CoroutineScope(Dispatchers.IO).launch {
            transcriptions.postValue(Loading)
            try {
                val loaded = Success(interactor.transcriptionFor(sentence))
                ifActive { transcriptions.postValue(loaded) }
            } catch (e: Exception) {
                ifActive { transcriptions.postValue(Error()) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchIpaJob?.cancel()
    }
}