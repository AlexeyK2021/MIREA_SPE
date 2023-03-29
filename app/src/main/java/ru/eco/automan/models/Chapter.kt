package ru.eco.automan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapter")
data class Chapter(
    @PrimaryKey val id:Int
)
