package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.Model

@Dao
interface ModelDao {

    @Query("SELECT * FROM model")
    fun getAllModels(): List<Model>

    @Query("SELECT * FROM model WHERE brand_id=:brandId")
    fun getModelsByBrandId(brandId: Int): List<Model>
}