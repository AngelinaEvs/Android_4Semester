package ru.itis.imagesearch.utils

import android.content.res.Resources
import retrofit2.HttpException
import ru.itis.imagesearch.R
import java.net.UnknownHostException

fun Throwable.getErrorMessage(resources: Resources): String = when {
    this is UnknownHostException -> resources.getString(R.string.no_internet)
    this is HttpException -> response()?.errorBody()?.string() ?: "Нет подключения к сети"
    else -> localizedMessage
}