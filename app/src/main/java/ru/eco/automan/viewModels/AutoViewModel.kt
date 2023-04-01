package ru.eco.automan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.dao.AutoDao
import ru.eco.automan.dao.BrandDao
import ru.eco.automan.dao.ModelDao
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Model

class AutoViewModel : ViewModel() {
    private var _autos: LiveData<List<Auto>>
    val userAutos get() = _autos.value

    private var _brands: List<Brand>
    val brands get() = _brands

    private var autoDao: AutoDao? = null
    private var brandDao: BrandDao? = null
    private var modelDao: ModelDao? = null


    init {
        autoDao = AutoApplication.database.autoDao()
        _autos = autoDao?.getAllAutosLiveData()!!

        brandDao = AutoApplication.database.brandDao()
        _brands = brandDao?.getAllBrands()!!

        modelDao = AutoApplication.database.modelDao()
    }

    fun addAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoDao?.addAuto(auto) }

    fun updateAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoDao?.updateAuto(auto)!! }

    fun deleteAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoDao?.deleteAuto(auto)!! }

    fun getModelsByAutoId(autoId: Int): LiveData<List<Model>> {
        val ret = MutableLiveData<List<Model>>()
        viewModelScope.launch(Dispatchers.IO) {
            ret.postValue(modelDao?.getModelsByBrandId(autoId))
        }
        return ret
    }
}