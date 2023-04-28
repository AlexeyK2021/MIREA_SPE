package ru.eco.automan.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.models.Category
import ru.eco.automan.models.Expense
import ru.eco.automan.repositories.ExpenseRepository
import java.sql.Date
import java.util.Calendar

/**
 * Преобразователь данных из базы данных для показа пользователю всего, что связано с тратами
 */
class ExpenseViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {
    private val userExpenses get() = expenseRepository.expenses
    private val categories get() = expenseRepository.categories

    fun addNewExpense(name: String, amount: Float, categoryId: Int, autoId: Int) {
//        val todayDate = Calendar.getInstance().time.date
        val current = Date(Calendar.getInstance().timeInMillis)
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