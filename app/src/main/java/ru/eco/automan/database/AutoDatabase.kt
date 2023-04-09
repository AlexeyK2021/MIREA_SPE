package ru.eco.automan.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.eco.automan.Converters
import ru.eco.automan.dao.*
import ru.eco.automan.models.*

/**
 * Класс базы данных, в котором описаны классы для автоматического создания таблиц.
 * @see Auto
 * @see Brand
 * @see Model
 * @see Category
 * @see Expense
 * @see FuelType
 * @see Chapter
 * @see Paragraph
 */
@Database(
    entities = [
        Auto::class,
        Brand::class,
        Model::class,
        Category::class,
        Expense::class,
        FuelType::class,
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
    abstract fun fuelTypeDao(): FuelTypeDao
    abstract fun brandDao(): BrandDao
    abstract fun modelDao(): ModelDao
    abstract fun chapterDao(): ChapterDao
    abstract fun paragraphDao(): ParagraphDao
}