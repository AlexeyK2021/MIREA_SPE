package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных бренда автомобиля
 * @param id ID-номер бренда автомобиля
 * @param name Имя бренда автомобиля
 */
@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo var name: String
)