package ru.eco.automan

import android.app.Application
import androidx.room.Room
import ru.eco.automan.database.AutoDatabase

class AutoApplication : Application() {
    companion object {
        private lateinit var database_: AutoDatabase
        val database get() = database_
    }


    override fun onCreate() {
        super.onCreate()
        database_ = Room.databaseBuilder(this, AutoDatabase::class.java, "auto_database.db")
            .createFromAsset("database/auto_DbBrowser.db")
            .build()

    }

}