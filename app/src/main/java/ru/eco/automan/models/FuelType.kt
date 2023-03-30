package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "fuel_type"
)
data class FuelType(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)
