package ru.eco.automan.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            EngineType::class,
            ["id"],
            ["engineTypeId"]
        )
    )
)
data class Auto(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val brand: String,
    @ColumnInfo val model: String,
    @ColumnInfo val registrationNumber: String,
    val engineTypeId: Int
)