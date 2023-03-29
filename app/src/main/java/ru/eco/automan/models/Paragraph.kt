package ru.eco.automan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paragraph")
data class Paragraph(
    @PrimaryKey val id: Int
)
