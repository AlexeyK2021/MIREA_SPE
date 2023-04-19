package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Expense

/**
 * Интерфейс, позволяющий получить доступ к тратам пользователя, хранящихся в базе данных
 * @see Expense
 */
@Dao
interface ExpenseDao {
    /**
     * Метод добавления новой траты в БД
     * @param expense Экземпляр новой траты
     */
    @Insert
    fun addExpense(expense: Expense)

    /**
     * Метод получения списка трат из БД
     * @return Список всех трат
     */
    @Query("SELECT * FROM expense")
    fun getAllExpenses(): List<Expense>

    /**
     * Метод получения списка трат в виде LiveData
     * @return Список всех трат в виде LiveData
     */
    @Query("SELECT * FROM expense")
    fun getAllExpensesLiveData(): LiveData<List<Expense>>

    /**
     * Метод обновления данных о трате
     * @param expense Экземпляр траты с новыми данными
     */
    @Update
    fun updateExpense(expense: Expense)

    /**
     * Метод удаления траты из БД
     * @param expense Экземпляр траты для удаления из БД
     */
    @Delete
    fun deleteExpense(expense: Expense)
}