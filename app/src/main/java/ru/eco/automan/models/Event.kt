package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * Класс данных о предстоящем событии
 * @param id ID-номер предстоящего собития
 * @param name Имя предстоящего события
 * @param date Дата предстоящего события
 * @param autoId ID-номер автомобиля, к которому относится событие
 */
@Entity(
    tableName = "event",
    foreignKeys = [
        ForeignKey(
            entity = Auto::class,
            parentColumns = ["id"],
            childColumns = ["auto_id"]
        )
    ]
)
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "auto_id") val autoId: Int
)