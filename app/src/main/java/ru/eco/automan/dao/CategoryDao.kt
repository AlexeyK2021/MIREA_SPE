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
    /**
     * Метод для добавления новой категории трат в БД
     * @param category Экземпляр новой категории
     */
    @Insert
    fun addCategory(category: Category)

    /**
     * Метод для получения списка всех категорий из БД
     * @return Список всех категорий из БД
     */
    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>

    /**
     * Метод для получения списка всех категорий в виде LiveData
     * @return Список всех категорий в виде LiveData
     */
    @Query("SELECT * FROM category")
    fun getAllCategoriesLiveData(): LiveData<List<Category>>

    /**
     * Метод обновления данных о существующей в БД категории
     * @param category Экземпляр категории трат с новыми данными
     */
    @Update
    fun updateCategory(category: Category)

    /**
     * Метод удаления категории трат из БД
     * @param category Экземпляр категории трат, подлежащий удалению
     */
    @Delete
    fun deleteCategory(category: Category)

}