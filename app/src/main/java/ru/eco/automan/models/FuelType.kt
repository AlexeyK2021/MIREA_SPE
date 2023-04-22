package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  Класс данных типа топлива автомобиля
 * @param id ID-номер типа топлива
 * @param name Имя типа топлива (бензин, дизель, электричество)
 */
@Entity(
    tableName = "fuel_type"
)
data class FuelType(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String
)
