package ru.eco.automan.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.eco.automan.dao.AutoDao
import ru.eco.automan.dao.CategoryDao
import ru.eco.automan.dao.EngineTypeDao
import ru.eco.automan.dao.ExpenseDao
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Category
import ru.eco.automan.models.EngineType
import ru.eco.automan.models.Expense

@Database(
    entities = [
        Auto::class,
        Category::class,
        Expense::class,
        EngineType::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AutoDatabase : RoomDatabase() {
    abstract fun autoDao(): AutoDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun engineTypeDao():EngineTypeDao
}