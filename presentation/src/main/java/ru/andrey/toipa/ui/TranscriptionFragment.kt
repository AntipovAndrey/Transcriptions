package ru.andrey.toipa.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.ipa_fragment.*
import ru.andrey.domain.model.Variant
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
            handleState(it!!)
        })
    }

    private fun handleState(state: TranscriptionsState) {
        if (state.result == null) {
            hideAll()
        } else {
            val transcriptions = state.result.transcriptions
            showAmerican(transcriptions[Variant.AMERICAN])
            showBritish(transcriptions[Variant.BRITISH])
        }

        progressBar.visibility = if (state.loading) View.VISIBLE else View.INVISIBLE

        if (state.error.contentIfNotHandled == true) {
            Snackbar.make(rootView, "Error", Snackbar.LENGTH_LONG).show();
        }
    }

    private fun getTranscriptionText(transcriptions: List<String>) = transcriptions.joinToString("\n")

    private fun showAmerican(transcriptions: List<String>?) {
        showIpa(transcriptions, Variant.AMERICAN)
    }

    private fun showBritish(transcriptions: List<String>?) {
        showIpa(transcriptions, Variant.BRITISH)
    }

    private fun showIpa(transcriptions: List<String>?, variant: Variant) {
        val card = if (variant == Variant.AMERICAN) americanCard else britishCard
        val text = if (variant == Variant.AMERICAN) ipaAmerican else ipaBritish

        if (transcriptions.isNullOrEmpty()) {
            card.visibility = View.GONE
        } else {
            card.visibility = View.VISIBLE
            text.text = getTranscriptionText(transcriptions)
        }
    }

    private fun hideAll() {
        americanCard.visibility = View.GONE
        britishCard.visibility = View.GONE
    }
}
