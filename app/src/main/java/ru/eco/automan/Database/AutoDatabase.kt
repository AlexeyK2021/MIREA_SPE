package ru.eco.automan.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.eco.automan.Dao.AutoDao
import ru.eco.automan.Dao.CategoryDao
import ru.eco.automan.Dao.ExpenseDao
import ru.eco.automan.Models.Category
import ru.eco.automan.Models.Expense

@Database(
    entities = [
        AutoDao::class,
        Category::class,
        Expense::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AutoDatabase : RoomDatabase() {
    abstract fun autoDao(): AutoDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
}