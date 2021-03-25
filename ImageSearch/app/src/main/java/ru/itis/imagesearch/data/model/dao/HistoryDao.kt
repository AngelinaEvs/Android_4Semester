package ru.itis.imagesearch.data.model.dao

import androidx.room.*
import ru.itis.imagesearch.data.model.entity.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBase(req: History)

    @Query("delete from History where req = :req")
    suspend fun deleteOldReq(req: String)

    @Query("select * from History")
    suspend fun getHistory(): List<String>

}