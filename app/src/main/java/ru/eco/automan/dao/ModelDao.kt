package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Model
/**
 * Интерфейс, позволяющий получить доступ к списку моделей автомобилей, хранящихся в базе данных
 * @see Model
 */
@Dao
interface ModelDao {

    @Query("SELECT * FROM model")
    fun getAllModels(): List<Model>

    @Query("SELECT * FROM model WHERE brand_id=:brandId")
    fun getModelsByBrandId(brandId: Int): List<Model>
}