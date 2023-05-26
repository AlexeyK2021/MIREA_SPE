package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Класс данных категории расхода на конкретный автомобиль
 * @param id ID-номер категории расходов
 * @param name Имя категории расходов
 * @param iconName Имя иконки для отображения
 */
@Entity(
    tableName = "category",
    foreignKeys = [
        ForeignKey(
            entity = Auto::class,
            parentColumns = ["id"],
            childColumns = ["auto_id"]
        )
    ]
)
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "icon_name") val iconName: String = "other_expenses",
    @ColumnInfo(name = "auto_id") val autoId: Int?
)
