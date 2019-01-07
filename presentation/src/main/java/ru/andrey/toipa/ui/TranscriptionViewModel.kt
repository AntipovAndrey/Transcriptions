package ru.andrey.toipa.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.andrey.domain.interactor.TranscriptionInteractor
import javax.inject.Inject

class TranscriptionViewModel @Inject constructor(private val interactor: TranscriptionInteractor) : ViewModel() {

    private val transcriptions: MutableLiveData<List<String>> = MutableLiveData()

    private var job: Job? = null

    fun observeTranscriptions(): LiveData<List<String>> = transcriptions

    fun setWord(word: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            job?.cancel()
            val result = interactor.transcriptionFor(word).transcriptions
            transcriptions.postValue(result)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}