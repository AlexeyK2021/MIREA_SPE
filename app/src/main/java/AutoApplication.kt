import android.app.Application
import androidx.room.Room
import ru.eco.automan.Database.AutoDatabase

class AutoApplication : Application() {
    private var _database: AutoDatabase? = null
    private var _instance: AutoApplication? = null

    val instance get() = _instance!!
    val database get() = _database!!

    override fun onCreate() {
        super.onCreate()
        _instance = this
        _database = Room.databaseBuilder(this, AutoDatabase::class.java, "auto_database")
            .build()
    }

}