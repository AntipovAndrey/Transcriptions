package ru.andrey.toipa.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.andrey.domain.interactor.TranscriptionInteractor
import javax.inject.Inject

class TranscriptionViewModel @Inject constructor(private val interactor: TranscriptionInteractor) : ViewModel() {

    private val transcriptions: MutableLiveData<List<String>> = MutableLiveData()

    fun observeTranscriptions(): LiveData<List<String>> = transcriptions

    fun setWord(word: String) {
        transcriptions.postValue(interactor.transcriptionFor(word).transcriptions)
    }
}