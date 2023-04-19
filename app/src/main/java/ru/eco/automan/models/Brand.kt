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
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)