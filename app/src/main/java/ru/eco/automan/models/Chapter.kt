package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных главы Правил Дорожного Движения
 * @param id ID-номер главы ПДД
 * @param name Имя главы ПДД
 */
@Entity(tableName = "chapter")
data class Chapter(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String
)
