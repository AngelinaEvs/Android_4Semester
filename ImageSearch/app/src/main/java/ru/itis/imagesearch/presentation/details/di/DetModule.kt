package ru.itis.imagesearch.presentation.details.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.itis.imagesearch.domain.FindImagesUseCase
import ru.itis.imagesearch.presentation.details.DetailsViewModel
import ru.itis.imagesearch.presentation.di.ViewModelKey
import ru.itis.imagesearch.presentation.di.ViewModelModule

@Module(includes = [ViewModelModule::class])
class DetModule {

    @Provides
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun provideViewModel(findImagesUseCase: FindImagesUseCase): ViewModel {
        return DetailsViewModel(findImagesUseCase)
    }

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): DetailsViewModel {
        return ViewModelProvider(fragment, viewModelFactory).get(DetailsViewModel::class.java)
    }

}