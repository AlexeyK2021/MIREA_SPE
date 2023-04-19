package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных категории расхода на конкретный автомобиль
 * @param id ID-номер категории расходов
 * @param name Имя категории расходов
 */
@Entity(tableName = "category")
data class Category(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String
)
