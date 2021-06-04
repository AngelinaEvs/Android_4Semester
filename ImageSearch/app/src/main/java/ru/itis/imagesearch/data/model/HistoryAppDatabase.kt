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
        private var instance: HistoryAppDatabase? = null

        @Synchronized
        fun get(context: Context): HistoryAppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryAppDatabase::class.java, "History.db"
                ).build()
            }
            return instance!!
        }

    }

}