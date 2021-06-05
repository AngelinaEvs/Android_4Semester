package ru.itis.imagesearch.presentation.main.horizontalList

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(
    private var list: List<String>,
    private val action: (String) -> Unit
) : RecyclerView.Adapter<SearchHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder.create(parent, action)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else {
            (payloads[0] as? Bundle)?.also {
                holder.updateFromBundle(it)
            } ?: run { super.onBindViewHolder(holder, position, payloads) }
        }
    }

    fun updateData(newList: List<String>) {
        val callback = SearchDiffUtilsCallBack(list, newList)
        val diffResult = DiffUtil.calculateDiff(callback, true)
        diffResult.dispatchUpdatesTo(this)
        list = mutableListOf<String>().apply { addAll(newList) }
    }

}