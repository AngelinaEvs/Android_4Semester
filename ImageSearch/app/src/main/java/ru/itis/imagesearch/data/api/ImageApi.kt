package ru.itis.imagesearch.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.imagesearch.data.api.response.ImageResponse

interface ImageApi {

    @GET("?lang=ru")
    suspend fun getMainList(@Query("order") param: String?): ImageResponse

    @GET("?lang=ru")
    suspend fun getResults(@Query("q") req: String): ImageResponse

    @GET("?lang=ru")
    suspend fun getImageById(@Query("id") id: Int): ImageResponse

}