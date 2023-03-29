package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Auto

@Dao
interface AutoDao {
    @Insert
    fun addAuto(auto: Auto)

    @Query("SELECT * FROM Auto")
    fun getAllAutos(): List<Auto>

    @Query("SELECT * FROM Auto")
    fun getAllAutosLiveData(): LiveData<List<Auto>>

    @Update
    fun updateAuto(auto: Auto)

    @Delete
    fun deleteAuto(auto: Auto)

}