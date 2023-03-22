package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Expense

@Dao
interface ExpenseDao {
    @Insert
    fun addExpense(expense: Expense): Boolean

    @Query("SELECT * FROM Auto")
    fun getAllAutos(): List<Auto>

    @Query("SELECT * FROM Auto")
    fun getAllExpensesLiveData(): LiveData<List<Expense>>

    @Update
    fun updateAuto(expense: Expense): Boolean

    @Delete
    fun deleteAuto(expense: Expense): Boolean
}