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
    @Insert
    fun addExpense(expense: Expense)

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): List<Expense>

    @Query("SELECT * FROM expense")
    fun getAllExpensesLiveData(): LiveData<List<Expense>>

    @Update
    fun updateExpense(expense: Expense)

    @Delete
    fun deleteExpense(expense: Expense)
}