package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.FuelType

@Dao
interface FuelTypeDao {
    @Query("SELECT * FROM fuel_type")
    fun getAllFuelTypes(): List<FuelType>
}