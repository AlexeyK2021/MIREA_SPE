package ru.eco.automan.models

import androidx.room.PrimaryKey

data class EngineType(
    @PrimaryKey val id: Int,
    @PrimaryKey val name: String
)
