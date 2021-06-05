package ru.itis.imagesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.imagesearch.domain.FindImagesUseCase
import ru.itis.imagesearch.presentation.details.DetailsViewModel
import ru.itis.imagesearch.presentation.main.MainListViewModel
import ru.itis.imagesearch.presentation.results.ResultsViewModel

class ViewModelFactory(
    private val findCityUseCase: FindImagesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainListViewModel::class.java) ->
                MainListViewModel(findCityUseCase) as T
            modelClass.isAssignableFrom(ResultsViewModel::class.java) ->
                ResultsViewModel(findCityUseCase) as T
            modelClass.isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(findCityUseCase) as T
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

}