package ru.eco.automan.models

import android.media.Image

data class AutoWithModelAndBrand(
    val id: Int,
    var name: String? = null,
    var brandName: String,
    var modelName: String,
    var autoIcon: Image? = null
)