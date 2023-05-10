package ru.eco.automan.models

import android.media.Image

/**
 * Класс для отображения списка автомобилей
 * @param id ID-номер объекта
 * @param name Имя
 * @param brandName имя бренда
 * @param modelName имя модели
 * @param autoIcon иконка автомобиля
 */
data class AutoWithModelAndBrand(
    val id: Int,
    var name: String? = null,
    var brandName: String,
    var modelName: String,
    var autoIcon: Image? = null
)