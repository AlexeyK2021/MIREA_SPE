package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Brand

@Dao
interface BrandDao {

    @Query("SELECT * FROM brand")
    fun getAllBrands(): List<Brand>

}