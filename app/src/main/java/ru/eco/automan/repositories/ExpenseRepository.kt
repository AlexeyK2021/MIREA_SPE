package ru.eco.automan.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.dao.CategoryDao
import ru.eco.automan.dao.ExpenseDao
import ru.eco.automan.models.Category
import ru.eco.automan.models.Expense
import kotlin.math.exp

/**
 * Пограничный класс-репозиторий, работающий с данными в базе данных и тратами пользователя и категориями этих трат
 * @see Expense
 * @see Category
 * @param expenseDao Dao для манипулирования данными о тратах пользователя
 * @param categoryDao Dao для манипулирования данными о категориях расходов пользователя
 */
class ExpenseRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao
) {
    private var _expenses: LiveData<List<Expense>>? = null
    val expenses get() = _expenses!!

    private var _categories: LiveData<List<Category>>? = null
    val categories get() = _categories!!

    init {
        runBlocking(Dispatchers.IO) {
            val catJob = launch {
                _categories = categoryDao.getAllCategoriesLiveData()
            }
            val expJob = launch {
                _expenses = expenseDao.getAllExpensesLiveData()
            }
            catJob.join()
            expJob.join()
        }
    }

    /**
     * Метод добавления новой траты пользователя в БД
     * @param expense Экземпляр траты с заполненными полями данных для внесения в БД
     */
    fun addExpense(expense: Expense) = expenseDao.addExpense(expense)

    /**
     * Метод обновления данных о существующей в БД трате пользователя
     * @param expense Экземпляр траты с новыми данными
     */
    fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)

    /**
     * Метод удаления существующей в БД траты
     * @param expense Экземпляр траты, подлежащей удалению
     */
    fun deleteExpense(expense: Expense) = expenseDao.deleteExpense(expense)

    /**
     * Метод добавления новой категории трат пользователя в БД
     * @param category Экземпляр категории трат с заполненными полями данных для внесения в БД
     */
    fun addCategory(category: Category) = categoryDao.addCategory(category)

    /**
     * Метод обновления данных о существующей в БД категории трат пользователя
     * @param category Экземпляр категории трат с новыми данными
     */
    fun updateCategory(category: Category) = categoryDao.updateCategory(category)

    /**
     * Метод удаления существующей в БД категории трат
     * @param category Экземпляр категории трат, подлежащей удалению
     */
    fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)

    /**
     * Метод удаления списка трат
     * @param expenseList список трат
     */
    fun deleteExpenses(expenseList: List<Expense>) = expenseDao.deleteExpenses(expenseList)

    /**
     * Метод удаления списка трат по ID-номеру автомобиля
     * @param autoId ID-номер автомобиля
     */
    fun deleteExpensesByAutoId(autoId: Int) = expenseDao.deleteExpensesByAutoId(autoId)

    /**
     * Метод удаления всех трат
     */
    fun deleteAllExpenses() = expenseDao.deleteAllExpenses()
}