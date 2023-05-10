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
     * Метод для добавления нового бренда в базу данных
     * @param brand экземпляр нового бренда для добавления
     */
    @Insert
    fun addBrand(brand: Brand)

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

    /**
     * Метод для получения ID-номера бренда по его имени
     * @param brand имя бренда, которому нужно найти ID
     * @return ID номер бренда
     */
    @Query("SELECT id FROM brand WHERE name=:brand")
    fun getBrandIdByName(brand: String): Int?

    /**
     * Метод для получения имени бренда по его ID
     * @param brandId ID номер бренда, для которого нужно получить название
     * @return название бренда
     */
    @Query("SELECT name FROM brand WHERE id=:brandId")
    fun getBrandNameById(brandId: Int): String?
}