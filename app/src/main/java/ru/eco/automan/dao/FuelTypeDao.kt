package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.Brand
import ru.eco.automan.models.FuelType
/**
 * Интерфейс, позволяющий получить доступ к списку типов автомобильного топлива, хранящихся в базе данных
 * @see FuelType
 */
@Dao
interface FuelTypeDao {
    @Query("SELECT * FROM fuel_type")
    fun getAllFuelTypes(): List<FuelType>
}