package ru.itis.imagesearch.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.itis.imagesearch.data.model.HistoryAppDatabase
import ru.itis.imagesearch.data.model.dao.HistoryDao
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideDB(context: Context): HistoryAppDatabase {
        return HistoryAppDatabase.get(context)
    }

    @Provides
    @Singleton
    fun provediClientDao(database: HistoryAppDatabase): HistoryDao = database.historyDao()
}