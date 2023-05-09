package ru.eco.automan.repositories

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.dao.EventDao
import ru.eco.automan.models.Event
import ru.eco.automan.viewModels.getEstimatedDays

/**
 * Пограничный класс-репозиторий, работающий с данными в базе данных и автомобилями пользователя, списками марок и соответствующими им моделями автомобилей
 * @see Event
 * @param eventDao Dao для манипулирования данными о предстоящих собыитях
 */
class EventsRepository(private val eventDao: EventDao) {
    private var _events: LiveData<List<Event>>? = null
    val events get() = _events!!

    init {
        runBlocking(Dispatchers.IO) {
            val eventsJob = launch {
                _events = eventDao.getAllEventsLiveData()
            }
            eventsJob.join()
        }
    }

    suspend fun getAllEvents(): List<Event> = eventDao.getAllEvents()

    fun addEvent(event: Event) = eventDao.addEvent(event)

    fun deleteEvent(eventId: Int) = eventDao.deleteEvent(eventId = eventId)
}