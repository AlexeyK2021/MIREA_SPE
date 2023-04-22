package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Brand

/**
 * Интерфейс, позволяющий получить доступ к списку брендов автомобилей, хранящихся в базе данных
 * @see Brand
 */
@Dao
interface BrandDao {
    /**
     * Метод для получения списка всех брендов автомобилей
     * @return Список брендов
     */
    @Query("SELECT * FROM brand")
    fun getAllBrands(): List<Brand>

    /**
     * Метод для получения списка всех брендов автомобилей в виде LiveData
     * @return Список брендов в виде LiveData
     */
    @Query("SELECT * FROM brand")
    fun getAllBrandsLiveData(): LiveData<List<Brand>>

    @Insert()
    fun addBrand(brand: Brand)
}