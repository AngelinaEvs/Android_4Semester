package ru.itis.imagesearch.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.itis.imagesearch.data.model.dao.HistoryDao
import ru.itis.imagesearch.data.model.entity.History

@Database(entities = [History::class], version = 1)
abstract class HistoryAppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {

        private const val DATABASE_NAME = "History.db"

        @Volatile
        private var instance: HistoryAppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, HistoryAppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

}