package ru.itis.imagesearch.presentation.main.verticalList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.image_main_list_cardview.*
import ru.itis.imagesearch.R
import ru.itis.imagesearch.data.api.response.Hit

class ImagesHolder(
        override val containerView: View,
        private val glideManager: RequestManager,
        private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(image: Hit) {
        glideManager.load(image.webformatURL).into(image_item)
        image_item.id = image.id
        itemView.setOnClickListener {
            action(image.id)
        }
    }

    fun updateFromBundle(bundle: Bundle) {
        if (bundle.containsKey("IMAGE_URL")) {
            bundle.getString("IMAGE_URL").also {
                image_item.id = it!!.toInt()
            }
        }
    }

    companion object {

        fun create(
                parent: ViewGroup,
                glideManager: RequestManager,
                action: (Int) -> Unit
        ): ImagesHolder = ImagesHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.image_main_list_cardview, parent, false),
                glideManager,
                action)
    }

}