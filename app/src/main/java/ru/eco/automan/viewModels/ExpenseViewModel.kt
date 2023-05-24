package ru.eco.automan.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
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
import kotlin.math.round


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

    fun editExpense(expenseId: Int, newExpenseName: String, newExpenseAmount: Float) {
        val expenseToEdit = userExpenses.value!!.find { it.id == expenseId }!!
        expenseToEdit.name = newExpenseName
        expenseToEdit.amount = newExpenseAmount

        viewModelScope.launch(Dispatchers.IO) { expenseRepository.updateExpense(expenseToEdit) }
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

    @SuppressLint("DiscouragedApi")
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
                    categoryImage = AppCompatResources.getDrawable(
                        context,
                        context.resources.getIdentifier(
                            category.iconName,
                            "drawable",
                            context.packageName
                        )
                    )!!,
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

    fun deleteAllExpensesByAutoId(autoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteExpensesByAutoId(autoId)
        }
    }

    fun deleteAllExpenses() {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.deleteAllExpenses()
        }
    }

    fun addNewCategory(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.addCategory(Category(id = 0, name = categoryName))
        }
    }

    fun getPercentageWastesByName(categoryName: String, autoId: Int, maxValue: Int): Int {
        var summ = 0f
        var allExpenses = expensesByPeriod(AutoApplication.periods[2].secondsNum)
        var categoryId: Int? = null
        while (categoryId == null)
            categoryId = getCategoryIdByName(categoryName)

        allExpenses.forEach {
            Log.d("getPercentageWastesByName", "Category find id $categoryId")
            if (it.autoId == autoId && it.categoryId == categoryId)
                summ += it.amount
        }
        return round(summ / maxValue * 100).toInt()
    }

    fun getPercentageOtherWastes(autoId: Int, context: Context): Int {
        var summ = 0
        expensesByPeriod(AutoApplication.periods[2].secondsNum).forEach {
            if (it.autoId == autoId && it.categoryId != getCategoryIdByName("Топливо")
                && it.categoryId != getCategoryIdByName("Ремонт")
            )
                summ += it.amount.toInt()
        }
        val maxSum = context.resources.getInteger(R.integer.max_other_wastes_sum)
        return summ / maxSum * 100
    }

}