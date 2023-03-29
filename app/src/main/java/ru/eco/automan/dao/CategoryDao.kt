package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Category

@Dao
interface CategoryDao {
    @Insert
    fun addCategory(category: Category)

    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM Category")
    fun getAllCategoriesLiveData(): LiveData<List<Category>>

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

}