package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *  Класс данных модели автомобиля
 * @param id ID-номер модели автомобиля
 * @param name Имя модели автомобиля
 * @param brandId ID-бренда, которому принадлежит эта модель
 */
@Entity(
    tableName = "model",
    foreignKeys = [
        ForeignKey(
            entity = Brand::class,
            parentColumns = ["id"],
            childColumns = ["brand_id"]
        )
    ]
)
data class Model(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "brand_id") val brandId: Int
)