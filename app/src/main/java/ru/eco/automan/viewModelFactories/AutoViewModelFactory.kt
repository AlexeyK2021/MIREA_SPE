package ru.eco.automan.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eco.automan.repositories.AutoRepository

class AutoViewModelFactory(private val autoRepository: AutoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AutoRepository::class.java).newInstance(autoRepository)
    }
}