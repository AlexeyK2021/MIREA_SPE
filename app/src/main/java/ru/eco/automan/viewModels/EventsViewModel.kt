package ru.eco.automan.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.models.Event
import ru.eco.automan.repositories.EventsRepository
import java.sql.Date

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    val autoEvents get() = eventsRepository.events

    fun getEventsNumberByAutoId(autoId: Int): Int {
        val number = autoEvents.value?.count { it.autoId == autoId }
        return if (number == null) 0 else number
    }

    fun getEventsByAutoId(autoId: Int): List<Event> {
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

}