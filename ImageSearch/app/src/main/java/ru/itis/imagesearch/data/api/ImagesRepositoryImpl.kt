package ru.itis.imagesearch.data.api

import android.content.Context
import ru.itis.imagesearch.data.api.response.ImageResponse
import ru.itis.imagesearch.data.model.HistoryAppDatabase
import ru.itis.imagesearch.data.model.LikedImagesAppDatabase
import ru.itis.imagesearch.data.model.entity.History
import ru.itis.imagesearch.data.model.entity.ImageEntity
import ru.itis.imagesearch.domain.ImagesRepository

class ImagesRepositoryImpl(
    private val imageApi: ImageApi, context: Context
) : ImagesRepository {
    private var db_image: LikedImagesAppDatabase = LikedImagesAppDatabase(context)
    private var db_history: HistoryAppDatabase = HistoryAppDatabase(context)

    override suspend fun getMainList(param: String?): ImageResponse = imageApi.getMainList(param)

    override suspend fun getResults(req: String): ImageResponse = imageApi.getResults(req)

    override suspend fun getLikedImages(): List<ImageEntity> = db_image.likedImagesDao().getAll()

    override suspend fun addReqToHistory(req: String) = db_history.historyDao().saveBase(History(req))

    override suspend fun getHistory(): List<String> = db_history.historyDao().getHistory()

    override suspend fun getImagesById(id: Int): ImageResponse = imageApi.getImageById(id)

}