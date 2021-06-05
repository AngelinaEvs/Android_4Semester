package ru.itis.imagesearch.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class History(
        @PrimaryKey
        @ColumnInfo(name = "req")
        var req: String
)