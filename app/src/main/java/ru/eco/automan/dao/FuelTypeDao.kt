package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.eco.automan.models.FuelType

/**
 * Интерфейс, позволяющий получить доступ к списку типов автомобильного топлива, хранящихся в базе данных
 * @see FuelType
 */
@Dao
interface FuelTypeDao {
    /**
     * Метод добавления нового типа топлива
     * @param fuelType экземпляр нового типа топлива
     */
    @Insert
    fun addFuelType(fuelType: FuelType)

    /**
     * Метод для получения списка всех типов топлива из БД
     * @return Список типов топлива
     */
    @Query("SELECT * FROM fuel_type")
    fun getAllFuelTypes(): List<FuelType>

    /**
     * Метод получения ID-номера топлива по имени
     * @param fuelTypeName имя топлива
     * @return ID-номер топлива.
     */
    @Query("SELECT id FROM fuel_type WHERE name=:fuelTypeName")
    fun getFuelTypeIdByName(fuelTypeName: String): Int?

    /**
     * Метод получения имени топлива по его ID-номеру
     * @param fuelTypeId ID-номер топлива
     * @return название топлива
     */
    @Query("SELECT name FROM fuel_type WHERE id=:fuelTypeId")
    fun getFuelTypeNameById(fuelTypeId: Int): String?

}