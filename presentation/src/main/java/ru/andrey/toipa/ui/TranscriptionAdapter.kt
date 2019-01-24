package ru.andrey.toipa.ui

import android.support.constraint.ConstraintLayout
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.andrey.domain.model.Transcription
import ru.andrey.domain.model.Variant
import ru.andrey.toipa.R

class TranscriptionAdapter : ListAdapter<Transcription, Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ipa_list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)

        val americanIpaList = item.transcriptions[Variant.AMERICAN]
        val britishIpaList = item.transcriptions[Variant.BRITISH]

        if (britishIpaList.isNullOrEmpty()) {
            adjustAmericanCardMargin(holder, 8)
        } else {
            adjustAmericanCardMargin(holder, 0)
        }

        showIpa(americanIpaList, holder.americanCard, holder.americanIpa)
        showIpa(britishIpaList, holder.britishCard, holder.britishIpa)
    }

    private fun adjustAmericanCardMargin(holder: Holder, dp: Int) {
        val card = holder.americanCard
        val px = Math.round(dp * (card.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        card.layoutParams = (card.layoutParams as ConstraintLayout.LayoutParams).apply { rightMargin = px }
    }

    private fun getTranscriptionText(transcriptions: List<String>) = transcriptions.joinToString("\n")

    private fun showIpa(transcriptions: List<String>?, card: View, text: TextView) {
        if (transcriptions.isNullOrEmpty()) {
            card.visibility = View.GONE
        } else {
            card.visibility = View.VISIBLE
            text.text = getTranscriptionText(transcriptions)
        }
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val americanCard: View = itemView.findViewById(R.id.americanCard)
    val britishCard: View = itemView.findViewById(R.id.britishCard)

    val americanIpa: TextView = itemView.findViewById(R.id.ipaAmerican)
    val britishIpa: TextView = itemView.findViewById(R.id.ipaBritish)
}

internal class DiffCallback : DiffUtil.ItemCallback<Transcription>() {

    override fun areItemsTheSame(old: Transcription, new: Transcription): Boolean {
        return old === new
    }

    override fun areContentsTheSame(old: Transcription, new: Transcription): Boolean {
        return old == new
    }
}
