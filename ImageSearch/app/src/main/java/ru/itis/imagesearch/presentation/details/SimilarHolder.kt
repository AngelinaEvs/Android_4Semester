package ru.itis.imagesearch.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.similar_card_view.*

import ru.itis.imagesearch.R
import ru.itis.imagesearch.data.api.response.Hit

class SimilarHolder(
        override val containerView: View,
        private val glideManager: RequestManager,
        private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(image: Hit) {
        glideManager.load(image.webformatURL).into(img_sim)
        img_sim.id = image.id
        itemView.setOnClickListener {
            action(image.id)
        }
    }

    fun updateFromBundle(bundle: Bundle) {
        if (bundle.containsKey("IMAGE_URL")) {
            bundle.getString("IMAGE_URL").also {
                img_sim.id = it!!.toInt()
            }
        }
    }

    companion object {

        fun create(
                parent: ViewGroup,
                glideManager: RequestManager,
                action: (Int) -> Unit
        ): SimilarHolder = SimilarHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.similar_card_view, parent, false),
                glideManager,
                action)
    }

}