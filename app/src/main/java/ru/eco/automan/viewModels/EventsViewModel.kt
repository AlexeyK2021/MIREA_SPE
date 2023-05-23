package ru.eco.automan.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.models.Event
import ru.eco.automan.repositories.EventsRepository
import java.sql.Date
import java.util.Calendar

/**
 * Метод для получения количества оставшихся дней до определенной даты
 */
fun Date.getEstimatedDays(): Int {
    val daysInMs = this.time - Calendar.getInstance().timeInMillis
    val days = daysInMs / AutoApplication.periods[0].secondsNum + 1
    return days.toInt()
}

/**
 * ViewModel, отвечающая за работу со всеми предстоящими событиями.
 * @param eventsRepository класс репозиторий, работающий с базой данных
 */
class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    val autoEvents get() = eventsRepository.events

    /**
     * Метод получения списка предстоящих событий по ID-номеру автомобиля
     * @param autoId ID-номер автомобиля
     * @return список предстоящих событий
     */
    fun getEventsByAutoId(autoId: Int): List<Event> {
        checkDate()
        val ret = mutableListOf<Event>()
        autoEvents.value?.forEach {
            if (it.autoId == autoId) ret.add(it)
        }
        return ret
    }

    /**
     * Метод добавления нового предстоящего события, параметры, имя, дата и айди, номер автомобиля.
     * @param name имя предстоящего события
     * @param date дата предстоящего события
     * @param autoId ID-номер автомобиля
     */
    fun addEvent(name: String, date: Date, autoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepository.addEvent(Event(id = 0, name = name, date = date, autoId = autoId))
        }
    }

    fun deleteEvent(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepository.deleteEvent(eventId)
        }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch (Dispatchers.IO) {
            eventsRepository.updateEvent(event)
        }
    }

    /**
     * Метод проверки существования предстоящего события.
     * Если дата события уже прошла, то оно удаляется из БД
     */
    private fun checkDate() {
        viewModelScope.launch(Dispatchers.IO) {
            autoEvents.value!!.forEach {
                if (it.date.getEstimatedDays() <= 0) {
                    Log.d("EventsRepository#Init", it.name)
                    eventsRepository.deleteEvent(it.id)
                }
            }
        }
    }
}