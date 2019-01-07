package ru.andrey.toipa.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ipa_fragment.*
import ru.andrey.toipa.R
import ru.andrey.toipa.utils.app
import ru.andrey.toipa.utils.debounce
import ru.andrey.toipa.utils.textChanged

class TranscriptionFragment : Fragment() {

    private lateinit var viewModel: TranscriptionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = app.transcriptionComponent.viewModelFactory().of(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.ipa_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        wordInput.addTextChangedListener(textChanged { viewModel.setWord(it) } debounce 300)
        viewModel.observeTranscriptions().observe(this, Observer {
            ipaText.text = it.toString()
        })
    }
}