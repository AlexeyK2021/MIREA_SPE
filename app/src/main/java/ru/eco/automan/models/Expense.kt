package ru.eco.automan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey val id: Int,
    @PrimaryKey val name: String
)
