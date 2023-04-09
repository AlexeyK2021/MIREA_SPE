package ru.eco.automan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.dao.ExpenseDao
import ru.eco.automan.models.Expense
import ru.eco.automan.models.Model

/**
 * Класс
 */
class ExpenseViewModel : ViewModel() {
    private var _expenses: LiveData<List<Expense>> = AutoApplication.expenseRepository.expenses

    fun addExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { }

    fun updateExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { }

    fun deleteExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) {  }
}