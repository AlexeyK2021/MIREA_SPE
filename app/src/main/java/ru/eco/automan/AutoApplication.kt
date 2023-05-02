package ru.eco.automan

import android.app.Application
import androidx.room.Room
import ru.eco.automan.database.AutoDatabase
import ru.eco.automan.models.Period
import ru.eco.automan.repositories.AutoRepository
import ru.eco.automan.repositories.ExpenseRepository
import ru.eco.automan.repositories.RulesRepository

/**
 * Класс приложения
 * Используется как хранилище со всеми зависимостями в приложении
 */
class AutoApplication : Application() {
    companion object {
        private lateinit var _database: AutoDatabase
        val database get() = _database

        private var autoRepo: AutoRepository? = null
        val autoRepository get() = autoRepo!!

        private var expenseRepo: ExpenseRepository? = null
        val expenseRepository get() = expenseRepo!!

        private var rulesRepo: RulesRepository? = null
        val rulesRepository get() = rulesRepo!!

        private var _notificationController: NotificationController? = null
        val notificationManager get() = _notificationController!!

        private var _periods: List<Period>? = null
        val periods get() = _periods!!
    }

    override fun onCreate() {
        super.onCreate()
        _database = Room.databaseBuilder(this, AutoDatabase::class.java, "auto_database.db")
            .createFromAsset("database/auto_DbBrowser.db")
            .build()

        autoRepo = AutoRepository(
            autoDao = _database.autoDao(),
            brandDao = _database.brandDao(),
            modelDao = _database.modelDao(),
            fuelTypeDao = _database.fuelTypeDao()
        )
        expenseRepo = ExpenseRepository(
            expenseDao = _database.expenseDao(),
            categoryDao = _database.categoryDao()
        )
        rulesRepo = RulesRepository(
            chapterDao = _database.chapterDao(),
            paragraphDao = _database.paragraphDao()
        )

        _notificationController = NotificationController()

        val periods = resources.getStringArray(R.array.wastes_periods)
        _periods = mutableListOf(
            Period(periods[0], 86_400_000),
            Period(periods[1], 604_800_000),
            Period(periods[2], 2_678_400_000)
        )
    }

}