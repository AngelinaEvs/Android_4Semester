package ru.itis.imagesearch.presentation.main.horizontalList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.searchcardview.*

import ru.itis.imagesearch.R

class SearchHolder(
    override val containerView: View,
    private val action: (String) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(str: String) {
        search_text.text = str
        itemView.setOnClickListener { action(str) }
    }

    fun updateFromBundle(bundle: Bundle) {
        if (bundle.containsKey("ARG_NAME")) {
            bundle.getString("ARG_NAME").also {
                search_text.text = it
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, action: (String) -> Unit): SearchHolder =
            SearchHolder(LayoutInflater.from(parent.context).inflate(R.layout.searchcardview, parent, false), action)
    }

}