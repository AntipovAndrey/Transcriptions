package ru.andrey.toipa.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
    private lateinit var adapter: TranscriptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = app.transcriptionComponent.viewModelFactory().of(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.ipa_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        adapter = TranscriptionAdapter()
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
        wordInput.addTextChangedListener(textChanged { viewModel.setSentence(it) } debounce 450)
        viewModel.observeTranscriptions().observe(this, Observer {
            handleState(it!!)
        })
    }

    private fun handleState(state: TranscriptionsState) {
        if (state.result.isEmpty()) {
            recycler.visibility = View.GONE
        } else {
            adapter.submitList(state.result)
            recycler.visibility = View.VISIBLE
        }

        progressBar.visibility = if (state.loading) View.VISIBLE else View.INVISIBLE

        if (state.error.contentIfNotHandled == true) {
            Snackbar.make(rootView, "Error", Snackbar.LENGTH_LONG).show()
        }
    }
}
