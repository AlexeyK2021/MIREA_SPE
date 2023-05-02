package ru.eco.automan.viewModels

import androidx.lifecycle.ViewModel
import ru.eco.automan.repositories.EventsRepository

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {

    fun getEventsNumber(): Int {
        return 31
        // TODO:
    }
}