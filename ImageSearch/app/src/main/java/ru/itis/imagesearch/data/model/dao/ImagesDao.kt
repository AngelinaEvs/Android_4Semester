package ru.itis.imagesearch.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.itis.imagesearch.data.model.entity.ImageEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBase(image: ImageEntity)

    @Query("delete from LikedImages where id = :id")
    suspend fun deleteOldReq(id: Int)

    @Query("select * from LikedImages")
    suspend fun getAll(): List<ImageEntity>

}