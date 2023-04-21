package ru.eco.automan.viewModels

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Brand
import ru.eco.automan.models.FuelType
import ru.eco.automan.models.Model
import ru.eco.automan.repositories.AutoRepository

/**
 * Преобразователь данных из базы данных для показа пользователю всего, что связано с автомобилями
 */

class AutoViewModel(private val autoRepository: AutoRepository) : ViewModel() {
    val userAutos get() = AutoApplication.autoRepository.autos
    val brands get() = AutoApplication.autoRepository.brands

    private var currAuto: LiveData<Auto>? = null
    val currentAuto get() = currAuto!!

    val currentAutoBrandName get() = getBrandNameById(currentAuto.value!!.brandId)
    val currentAutoModelName get() = getModelNameById(currentAuto.value!!.modelId)
    val currentAutoFuelTypeName get() = getFuelTypeNameById(currentAuto.value!!.fuelTypeId)

    fun addAuto(auto: Auto) = viewModelScope.launch(Dispatchers.IO) {
        autoRepository.addAuto(auto = auto)
    }

    fun updateAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoRepository.updateAuto(auto = auto) }

    fun deleteAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoRepository.deleteAuto(auto = auto) }

    fun getModelsByBrand(brand: Brand): LiveData<List<Model>> {
        val brands = MutableLiveData<List<Model>>()
        viewModelScope.launch(Dispatchers.IO) {
            brands.value = autoRepository.getModelsByBrand(brand = brand)
        }
        return brands
    }

    private fun getBrandNameById(brandId: Int): String =
        autoRepository.brands.find { it.id == brandId }!!.name

    private fun getModelNameById(modelId: Int): String =
        autoRepository.models.find { it.id == modelId }!!.name

    private fun getFuelTypeNameById(fuelTypeId: Int): String =
        autoRepository.fuelTypes.find { it.id == fuelTypeId }!!.name

}