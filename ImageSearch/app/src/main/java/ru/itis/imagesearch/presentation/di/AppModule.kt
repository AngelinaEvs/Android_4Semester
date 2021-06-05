package ru.itis.imagesearch.presentation.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import ru.itis.imagesearch.domain.FindImagesUseCase
import ru.itis.imagesearch.domain.ImagesRepository
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: ImagesRepository): FindImagesUseCase {
        return FindImagesUseCase(repository, Dispatchers.IO)
    }

    @Provides
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO


}