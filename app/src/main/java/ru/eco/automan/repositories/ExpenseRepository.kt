package ru.eco.automan.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.dao.ExpenseDao
import ru.eco.automan.models.Expense

class ExpenseRepository : ViewModel() {
    private var _expenses: LiveData<List<Expense>>
    val expenses get() = _expenses.value
    private var expenseDao: ExpenseDao? = null

    init {
        expenseDao = AutoApplication.database.expenseDao()
        _expenses = expenseDao?.getAllExpensesLiveData()!!
    }

    fun addExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { expenseDao?.addExpense(expense) }

    fun updateExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { expenseDao?.updateAuto(expense)!! }

    fun deleteExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { expenseDao?.deleteAuto(expense)!! }
}