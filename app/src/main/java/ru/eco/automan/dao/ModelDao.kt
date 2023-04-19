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
    /**
     * Метод для получения списка всех моделей
     * @return Список всех моделей автомобилей
     */
    @Query("SELECT * FROM model")
    fun getAllModels(): List<Model>

    /**
     * Метод для получения списка моделей по бренду автомобиля
     * @param brandId ID-номер бренда, для которого необходимо вернуть список моделей
     * @return Список моделей по ID-номеру бренда автомобиля
     */
    @Query("SELECT * FROM model WHERE brand_id=:brandId")
    fun getModelsByBrandId(brandId: Int): List<Model>
}