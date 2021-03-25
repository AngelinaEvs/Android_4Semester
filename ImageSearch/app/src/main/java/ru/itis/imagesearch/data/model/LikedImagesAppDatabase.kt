package ru.itis.imagesearch.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.itis.imagesearch.data.model.dao.ImagesDao
import ru.itis.imagesearch.data.model.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class LikedImagesAppDatabase : RoomDatabase() {

    abstract fun likedImagesDao(): ImagesDao

    companion object {

        private const val DATABASE_NAME = "LikedImages.db"

        @Volatile
        private var instance: LikedImagesAppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, LikedImagesAppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }

}