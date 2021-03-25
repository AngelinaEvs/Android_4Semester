package ru.itis.imagesearch.domain

import kotlinx.coroutines.withContext
import ru.itis.imagesearch.data.api.response.ImageResponse
import kotlin.coroutines.CoroutineContext

class FindImagesUseCase(
    private val imagesRepository: ImagesRepository,
    private val context: CoroutineContext
) {

    suspend fun findMainListImages(param: String?): ImageResponse =
        withContext(context) {
            if (param == null) imagesRepository.getMainList("popular")
            else imagesRepository.getMainList(param)
        }

    suspend fun findResults(req: String): ImageResponse =
        withContext(context) {
            imagesRepository.getResults(req)
        }

    suspend fun addReqToHistory(req: String) =
        withContext(context) {
            imagesRepository.addReqToHistory(req)
        }

    suspend fun gitHistory() =
        withContext(context) {
            imagesRepository.getHistory()
        }

    suspend fun findImagesById(id: Int) =
            withContext(context) {
                imagesRepository.getImagesById(id)
            }

}