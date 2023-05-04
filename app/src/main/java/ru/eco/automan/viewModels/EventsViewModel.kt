package ru.eco.automan.viewModels

import androidx.lifecycle.ViewModel
import ru.eco.automan.repositories.EventsRepository

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    val autoEvents get() = eventsRepository.events

    fun getEventsNumberByAutoId(autoId: Int): Int {
        val number = autoEvents.value?.count { it.autoId == autoId }
        return if (number == null) 0 else number
    }
}