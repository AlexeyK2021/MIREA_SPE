package ru.eco.automan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Model

/**
 * Преобразователь данных из базы данных для показа пользователю всего, что связано с автомобилями
 */

class AutoViewModel : ViewModel() {

    val userAutos get() = AutoApplication.autoRepository.autos
    val brands get() = AutoApplication.autoRepository.brands
    fun addAuto(auto: Auto) = viewModelScope.launch(Dispatchers.IO) {

    }

    fun updateAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { }

    fun deleteAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { }

    fun getModelsByAutoId(autoId: Int): LiveData<List<Model>> {
    }
}