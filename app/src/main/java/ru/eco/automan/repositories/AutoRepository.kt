package ru.eco.automan.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.dao.AutoDao
import ru.eco.automan.models.Auto

class AutoRepository : ViewModel() {
    private var _autos: LiveData<List<Auto>>
    val autos get() = _autos.value
    private var autoDao: AutoDao? = null

    init {
        autoDao = AutoApplication.database.autoDao()
        _autos = autoDao?.getAllAutosLiveData()!!
    }

    fun addAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoDao?.addAuto(auto) }

    fun updateAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoDao?.updateAuto(auto)!! }

    fun deleteAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoDao?.deleteAuto(auto)!! }
}