package ru.eco.automan.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ru.eco.automan.Converters
import ru.eco.automan.dao.*
import ru.eco.automan.models.*

@Database(
    entities = [
        Auto::class,
        Brand::class,
        Model::class,
        Category::class,
        Expense::class,
        EngineType::class,
        Chapter::class,
        Paragraph::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AutoDatabase : RoomDatabase() {
    abstract fun autoDao(): AutoDao
    abstract fun categoryDao(): CategoryDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun engineTypeDao(): EngineTypeDao
}