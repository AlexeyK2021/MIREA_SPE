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

fun Date.getEstimatedDays(): Int {
    val daysInMs = this.time - Calendar.getInstance().timeInMillis
    val days = daysInMs / AutoApplication.periods[0].secondsNum + 1
    return days.toInt()
}

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    val autoEvents get() = eventsRepository.events

    fun getEventsByAutoId(autoId: Int): List<Event> {
        checkDate()
        val ret = mutableListOf<Event>()
        autoEvents.value?.forEach {
            if (it.autoId == autoId) ret.add(it)
        }
        return ret
    }

    fun addEvent(name: String, date: Date, autoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            eventsRepository.addEvent(Event(id = 0, name = name, date = date, autoId = autoId))
        }
    }

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