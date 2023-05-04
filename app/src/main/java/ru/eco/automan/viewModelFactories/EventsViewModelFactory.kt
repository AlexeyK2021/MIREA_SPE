package ru.eco.automan.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eco.automan.repositories.AutoRepository
import ru.eco.automan.repositories.EventsRepository

class EventsViewModelFactory(private val eventsRepository: EventsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(EventsRepository::class.java).newInstance(eventsRepository)
    }
}