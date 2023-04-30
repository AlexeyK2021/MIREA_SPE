package ru.eco.automan.viewModels

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.R
import ru.eco.automan.models.Category
import ru.eco.automan.models.CategoryWithExpenseAndIcon
import ru.eco.automan.models.Expense
import ru.eco.automan.repositories.ExpenseRepository
import java.sql.Date
import java.util.Calendar

/**
 * Преобразователь данных из базы данных для показа пользователю всего, что связано с тратами
 */
class ExpenseViewModel(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    val userExpenses get() = expenseRepository.expenses
    val categories get() = expenseRepository.categories

    private var currAutoId: Int? = null

    fun addNewExpense(name: String, amount: Float, categoryName: String) {
//        val todayDate = Calendar.getInstance().time.date
        viewModelScope.launch(Dispatchers.IO) {
            val current = Date(Calendar.getInstance().timeInMillis)
            val newExpense = Expense(
                id = 0,
                name = name,
                amount = amount,
                categoryId = getCategoryIdByName(categoryName)!!,
                autoId = currAutoId!!,
                date = current
            )
            expenseRepository.addExpense(newExpense)
        }
    }

    fun deleteExpense(expenseId: Int) {
        val expenseToDelete = userExpenses.value!!.find { it.id == expenseId }!!
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteExpense(expense = expenseToDelete)
        }
    }

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

    fun getCategoryWithExpensesAndIcon(context: Context): List<CategoryWithExpenseAndIcon> {
        val ret = mutableListOf<CategoryWithExpenseAndIcon>()

        Log.d("getCategoryWithExpensesAndIcon", expenseRepository.categories.value.toString())
        expenseRepository.categories.value?.forEach { category ->
            val expenses = mutableListOf<Expense>()
            userExpenses.value?.forEach { e ->
                if (e.id == currAutoId && category.id == e.categoryId)
                    expenses.add(e)
            }

            ret.add(
                CategoryWithExpenseAndIcon(
                    categoryName = category.name,
                    categoryImage = ContextCompat.getDrawable(context, R.drawable.icon_book)!!,
                    expensesList = expenses,
                    wastesSum = sumOfExpenseList(expenses)
                )
            )
        }
        return ret
    }

    private fun sumOfExpenseList(expenses: List<Expense>): Float {
        var sum = 0f
        expenses.forEach { sum += it.amount }
        return sum
    }

    fun getExpensesByPeriod(position: Int): List<CategoryWithExpenseAndIcon> {
        return mutableListOf()
    }

    fun setCurrentAuto(autoId: Int) {
        currAutoId = autoId
    }

    private fun getCategoryNameById(categoryId: Int): String? =
        expenseRepository.categories.value?.find { it.id == categoryId }?.name

    private fun getCategoryIdByName(category: String): Int? =
        expenseRepository.categories.value?.find { it.name == category }?.id

    fun deleteAllExpenses(autoId: Int) {
        val expensesToDelete = mutableListOf<Expense>()
        viewModelScope.launch(Dispatchers.IO) {
            userExpenses.value!!.forEach {
                if (it.autoId == autoId) expensesToDelete.add(it)
            }
            expenseRepository.deleteExpenses(expensesToDelete)
        }
    }
}