package ru.eco.automan.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eco.automan.repositories.AutoRepository
import ru.eco.automan.repositories.RulesRepository

class RulesViewModelFactory(private val rulesRepository: RulesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RulesRepository::class.java).newInstance(rulesRepository)
    }
}