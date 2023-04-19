package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Auto

/**
 * Интерфейс, позволяющий получить доступ к данным об автомобилях пользователя, хранящихся в базе данных
 * @see Auto
 */
@Dao
interface AutoDao {
    /**
     * Метод добавления нового автомобиля в БД
     * @param auto Экземпляр для добавления в БД
     */
    @Insert
    fun addAuto(auto: Auto)

    /**
     * Метод получения списка всех автомобилей в БД
     * @return Список автомобилей
     */
    @Query("SELECT * FROM auto")
    fun getAllAutos(): List<Auto>

    /**
     * Метод получения списка всех автомобилей в БД в формате списка LiveData
     * @return Список с уведомлением об обновлении данных (LiveData)
     */
    @Query("SELECT * FROM auto")
    fun getAllAutosLiveData(): LiveData<List<Auto>>

    /**
     * Метод получения списка авмтомобиля по его ID в БД
     * @param autoId ID-номер автомобиля
     * @return Экземпляр соответствующего экземпляра автомобиля
     */
    @Query("SELECT * FROM auto WHERE id=:autoId")
    fun findById(autoId: Int): Auto

    /**
     * Метод обновления информации об автомобиле
     * @param auto Экземпляр автомобиля с новыми данными
     */
    @Update
    fun updateAuto(auto: Auto)

    /**
     * Метод для удаления информации об автомобиле из БД
     * @param auto Экзепляр автомобиля, подлежащего удалению
     */
    @Delete
    fun deleteAuto(auto: Auto)

}