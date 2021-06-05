package ru.itis.imagesearch.presentation.main.horizontalList

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

class SearchDiffUtilsCallBack(
    private var oldList: List<String>,
    private var newList: List<String>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]//.name
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle().apply {
            if (oldList[oldItemPosition] != (newList[newItemPosition])) {
                putString("ARG_NAME", newList[newItemPosition])
            }
        }
        return if (bundle.isEmpty) null else bundle
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
