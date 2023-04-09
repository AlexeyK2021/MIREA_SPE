package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Category
/**
 * Интерфейс, позволяющий получить доступ к списку категорий трат пользователя, хранящихся в базе данных
 * @see Category
 */
@Dao
interface CategoryDao {
    @Insert
    fun addCategory(category: Category)

    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM category")
    fun getAllCategoriesLiveData(): LiveData<List<Category>>

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

}