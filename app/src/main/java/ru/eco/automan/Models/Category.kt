package ru.eco.automan.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey val id: Int,
    @PrimaryKey val name: String
)
