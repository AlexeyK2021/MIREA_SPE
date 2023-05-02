package ru.eco.automan.viewModels

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.models.Category
import ru.eco.automan.models.CategoryWithExpenseAndIcon
import ru.eco.automan.models.Expense
import ru.eco.automan.models.Period
import ru.eco.automan.repositories.ExpenseRepository
import java.sql.Date
import java.util.Calendar
import java.time.LocalDateTime.now


/**
 * Преобразователь данных из базы данных для показа пользователю всего, что связано с тратами
 */
class ExpenseViewModel(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {
    val userExpenses get() = expenseRepository.expenses
    val categories get() = expenseRepository.categories

    private var currAutoId: Int? = null

    private var currentPeriod: Period? = null

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

    private fun getCategoryWithExpensesAndIcon(
        context: Context,
        userExpenses: List<Expense>?
    ): List<CategoryWithExpenseAndIcon> {
        val ret = mutableListOf<CategoryWithExpenseAndIcon>()

        Log.d("getCategoryWithExpensesAndIcon", categories.value.toString())
        categories.value?.forEach { category ->
            val expenses = mutableListOf<Expense>()
            userExpenses?.forEach { e ->
                if (e.autoId == currAutoId && e.categoryId == category.id)
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

    fun getCategoryWithExpensesAndIcon(context: Context): List<CategoryWithExpenseAndIcon> =
        getCategoryWithExpensesAndIcon(context, userExpenses.value)


    private fun sumOfExpenseList(expenses: List<Expense>): Float {
        var sum = 0f
        expenses.forEach { sum += it.amount }
        return sum
    }

    fun getExpensesByPeriod(
        position: Int,
        lastPeriod: Int,
        context: Context
    ): List<CategoryWithExpenseAndIcon> {
        return when (position) {
            lastPeriod, lastPeriod - 1 -> getCategoryWithExpensesAndIcon(context)
            else -> getCategoryWithExpensesAndIcon(
                context,
                expensesByPeriod(AutoApplication.periods[position].secondsNum)
            )
        }
    }

    fun expensesByPeriod(millis: Long): List<Expense> {
        val ret = mutableListOf<Expense>()
        userExpenses.value?.forEach {
            Log.d(
                "expensesByPeriod",
                " ${it.name} - Delta Time: ${Calendar.getInstance().timeInMillis - it.date.time}"
            )
            if (Calendar.getInstance().timeInMillis - it.date.time < millis) {
                ret.add(it)
            }
        }
        return ret
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