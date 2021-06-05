package ru.itis.imagesearch.domain

import ru.itis.imagesearch.data.api.response.ImageResponse
import ru.itis.imagesearch.data.model.entity.ImageEntity

interface ImagesRepository {

    suspend fun getMainList(param: String?): ImageResponse

    suspend fun getResults(req: String): ImageResponse

    suspend fun getLikedImages(): List<ImageEntity>

    suspend fun addReqToHistory(req: String)

    suspend fun getHistory(): List<String>

    suspend fun getImagesById(id: Int): ImageResponse

}