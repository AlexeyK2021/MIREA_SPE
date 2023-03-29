package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "engine_type"
)
data class EngineType(
    @PrimaryKey val id: Int,
    @ColumnInfo val type: String
)
