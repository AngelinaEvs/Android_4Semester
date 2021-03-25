package ru.itis.imagesearch.presentation.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.imagesearch.data.api.response.Hit

class SimilarAdapter(
        private var list: List<Hit>,
        private val glideManager: RequestManager,
        private val action: (Int) -> Unit
) : RecyclerView.Adapter<SimilarHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder {
        return SimilarHolder.create(parent, glideManager, action)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        holder.bind(list[position])
    }

}