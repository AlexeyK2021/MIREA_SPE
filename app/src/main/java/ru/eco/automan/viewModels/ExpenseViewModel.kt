package ru.eco.automan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.dao.ExpenseDao
import ru.eco.automan.models.Category
import ru.eco.automan.models.Expense
import ru.eco.automan.models.Model
import ru.eco.automan.repositories.ExpenseRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

/**
 * Класс
 */
class ExpenseViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {
    private val userExpenses get() = expenseRepository.expenses
    private val categories get() = expenseRepository.categories

    fun addNewExpense(name: String, amount: Float, categoryId: Int, autoId: Int) {
//        val todayDate = Calendar.getInstance().time.date
        val current = Date()
        val newExpense = Expense(
            id = 0,
            name = name,
            amount = amount,
            categoryId = categoryId,
            autoId = autoId,
            date = current
        )
//        expenseRepository.addExpense(newExpense)
        addExpense(newExpense)
    }

    fun deleteExpense(expenseId: Int) {
        val expenseToDelete = userExpenses.value!!.find { it.id == expenseId }!!
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteExpense(expense = expenseToDelete)
        }
    }

    private fun addExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { expenseRepository.addExpense(expense) }

    fun updateExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { }

    fun deleteExpense(expense: Expense) =
        viewModelScope.launch(Dispatchers.IO) { }

    fun getExpensesByAutoId(autoId: Int): Map<Category, Expense> {
        val dataToReturn = HashMap<Category, Expense>()
        userExpenses.value!!.forEach { e ->
            val cat = categories.value!!.find { it.id == e.categoryId }!!
            dataToReturn[cat] = e
        }
//        userExpenses.value!!.find { it.id == autoId }
        return dataToReturn
    }
}