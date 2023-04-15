package ru.eco.automan.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eco.automan.repositories.AutoRepository
import ru.eco.automan.repositories.ExpenseRepository

class ExpenseViewModelFactory(private val expenseRepository: ExpenseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ExpenseRepository::class.java)
            .newInstance(expenseRepository)
    }
}