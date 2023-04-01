package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Auto

@Dao
interface AutoDao {
    @Insert
    fun addAuto(auto: Auto)

    @Query("SELECT * FROM auto")
    fun getAllAutos(): List<Auto>

    @Query("SELECT * FROM auto")
    fun getAllAutosLiveData(): LiveData<List<Auto>>

    @Query("SELECT * FROM auto WHERE id=:autoId")
    fun findById(autoId: Int):Auto

    @Update
    fun updateAuto(auto: Auto)

    @Delete
    fun deleteAuto(auto: Auto)


}