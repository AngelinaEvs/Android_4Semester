package ru.itis.imagesearch.data.api

import android.content.Context
import ru.itis.imagesearch.data.api.response.ImageResponse
import ru.itis.imagesearch.data.model.HistoryAppDatabase
import ru.itis.imagesearch.data.model.LikedImagesAppDatabase
import ru.itis.imagesearch.data.model.dao.HistoryDao
import ru.itis.imagesearch.data.model.dao.ImagesDao
import ru.itis.imagesearch.data.model.entity.History
import ru.itis.imagesearch.data.model.entity.ImageEntity
import ru.itis.imagesearch.domain.ImagesRepository

class ImagesRepositoryImpl(
    private val imageApi: ImageApi,
    private val dao: ImagesDao,
    private val daoH: HistoryDao
) : ImagesRepository {

    override suspend fun getMainList(param: String?): ImageResponse = imageApi.getMainList(param)

    override suspend fun getResults(req: String): ImageResponse = imageApi.getResults(req)

    override suspend fun getLikedImages(): List<ImageEntity> = dao.getAll()

    override suspend fun addReqToHistory(req: String) = daoH.saveBase(History(req))

    override suspend fun getHistory(): List<String> = daoH.getHistory()

    override suspend fun getImagesById(id: Int): ImageResponse = imageApi.getImageById(id)

}