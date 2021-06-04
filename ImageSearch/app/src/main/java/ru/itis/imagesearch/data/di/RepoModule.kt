package ru.itis.imagesearch.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.itis.imagesearch.data.api.ImageApi
import ru.itis.imagesearch.data.api.ImagesRepositoryImpl
import ru.itis.imagesearch.data.model.dao.HistoryDao
import ru.itis.imagesearch.data.model.dao.ImagesDao
import ru.itis.imagesearch.domain.ImagesRepository
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideClientRepository(
        dao: ImagesDao,
        api: ImageApi,
        daoH: HistoryDao
    ): ImagesRepository {
        return ImagesRepositoryImpl(api, dao, daoH)
    }

}