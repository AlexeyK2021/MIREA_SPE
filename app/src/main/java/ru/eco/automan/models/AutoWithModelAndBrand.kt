package ru.eco.automan.models

data class AutoWithModelAndBrand(
    val id: Int,
    var name: String? = null,
    var brandName:String,
    var modelName: String
)