package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)