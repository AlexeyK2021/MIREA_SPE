package ru.eco.automan.repositories

import androidx.lifecycle.LiveData
import ru.eco.automan.dao.CategoryDao
import ru.eco.automan.dao.ExpenseDao
import ru.eco.automan.models.Category
import ru.eco.automan.models.Expense

/**
 * Пограничный класс-репозиторий, работающий с данными в базе данных и тратами пользователя и категориями этих трат
 * @see Expense
 * @see Category
 */
class ExpenseRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao
) {
    private var _expenses: LiveData<List<Expense>> = expenseDao.getAllExpensesLiveData()
    val expenses get() = _expenses

    private var _categories: LiveData<List<Category>> = categoryDao.getAllCategoriesLiveData()
    val categories get() = _categories

    fun addExpense(expense: Expense) = expenseDao.addExpense(expense)

    fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)

    fun deleteExpense(expense: Expense) = expenseDao.deleteExpense(expense)

    fun addCategory(category: Category) = categoryDao.addCategory(category)

    fun updateCategory(category: Category) = categoryDao.updateCategory(category)

    fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)
    
}