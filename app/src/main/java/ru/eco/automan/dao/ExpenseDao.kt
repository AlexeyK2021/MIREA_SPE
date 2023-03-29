package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Expense

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