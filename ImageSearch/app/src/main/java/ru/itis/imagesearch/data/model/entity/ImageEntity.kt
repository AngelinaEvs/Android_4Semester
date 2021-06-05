package ru.itis.imagesearch.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LikedImages")
data class ImageEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "tags")
    var tags: String,
)