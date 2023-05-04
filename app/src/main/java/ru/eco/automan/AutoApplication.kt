package ru.eco.automan

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.NotificationController.Companion.CHANNEL_ID
import ru.eco.automan.database.AutoDatabase
import ru.eco.automan.models.Period
import ru.eco.automan.repositories.AutoRepository
import ru.eco.automan.repositories.EventsRepository
import ru.eco.automan.repositories.ExpenseRepository
import ru.eco.automan.repositories.RulesRepository

/**
 * Класс приложения
 * Используется как хранилище со всеми зависимостями в приложении
 */
class AutoApplication : Application() {
    private val APP_PREFERENCES = "settings"

    companion object {
        private lateinit var _database: AutoDatabase
        val database get() = _database

        private var autoRepo: AutoRepository? = null
        val autoRepository get() = autoRepo!!

        private var expenseRepo: ExpenseRepository? = null
        val expenseRepository get() = expenseRepo!!

        private var rulesRepo: RulesRepository? = null
        val rulesRepository get() = rulesRepo!!

        private var eventsRepo: EventsRepository? = null
        val eventsRepository get() = eventsRepo!!

        private var sharedPrefs: SharedPreferences? = null
        val sharedPreferences get() = sharedPrefs!!

        private var _notificationController: NotificationController? = null
        val notificationManager get() = _notificationController!!

        private var _periods: List<Period>? = null
        val periods get() = _periods!!
    }

    override fun onCreate() {
        super.onCreate()
        sharedPrefs = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        _database = Room.databaseBuilder(this, AutoDatabase::class.java, "auto_database.db")
            .createFromAsset("database/auto_DbBrowser.db")
            .build()

        runBlocking {
            val autoRepoJob = launch {
                autoRepo = AutoRepository(
                    autoDao = _database.autoDao(),
                    brandDao = _database.brandDao(),
                    modelDao = _database.modelDao(),
                    fuelTypeDao = _database.fuelTypeDao()
                )
                Log.d("AutoApplication#onCreate", "autoRepo created")
            }
            val expenseRepoJob = launch {
                expenseRepo = ExpenseRepository(
                    expenseDao = _database.expenseDao(),
                    categoryDao = _database.categoryDao()
                )
                Log.d("AutoApplication#onCreate", "expenseRepo created")
            }
            val rulesRepoJob = launch {
                rulesRepo = RulesRepository(
                    chapterDao = _database.chapterDao(),
                    paragraphDao = _database.paragraphDao()
                )
                Log.d("AutoApplication#onCreate", "rulesRepo created")
            }
            val eventsRepoJob = launch {
                eventsRepo = EventsRepository()
                Log.d("AutoApplication#onCreate", "eventsRepo created")
            }
            autoRepoJob.join()
            expenseRepoJob.join()
            rulesRepoJob.join()
            eventsRepoJob.join()
        }

        _notificationController = NotificationController()

        val periods = resources.getStringArray(R.array.wastes_periods)
        _periods = mutableListOf(
            Period(periods[0], 86_400_000),
            Period(periods[1], 604_800_000),
            Period(periods[2], 2_678_400_000)
        )
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.events)
            val descriptionText = getString(R.string.events_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}