package ru.eco.automan.viewModels

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.models.Auto
import ru.eco.automan.models.AutoWithModelAndBrand
import ru.eco.automan.models.Brand
import ru.eco.automan.models.FuelType
import ru.eco.automan.models.Model
import ru.eco.automan.repositories.AutoRepository

/**
 * Преобразователь данных из базы данных для показа пользователю всего, что связано с автомобилями
 */

class AutoViewModel(private val autoRepository: AutoRepository) : ViewModel() {
    class NewAutoData {
        var brandId: Int = 0
        var modelId: Int = 0
        var manufactureYear: Int = 0
        var fuelTypeId: Int = 0
        var registrationCertificateNumber: String = ""
    }

    val userAutos get() = autoRepository.autos
    val brands get() = autoRepository.brands
    val fuelTypes get() = autoRepository.fuelTypes

    var currAuto: MutableLiveData<Auto> = MutableLiveData()

    val currentAutoBrandName get() = getBrandNameById(currAuto.value!!.brandId)
    val currentAutoModelName get() = getModelNameById(currAuto.value!!.modelId)
    val currentAutoFuelTypeName get() = getFuelTypeNameById(currAuto.value!!.fuelTypeId)

    private lateinit var newAuto: NewAutoData


    fun updateAuto(auto: Auto) =
        viewModelScope.launch(Dispatchers.IO) { autoRepository.updateAuto(auto = auto) }

    fun deleteAuto(autoId: Int) {
        val autoToDelete = userAutos.value!!.find { it.id == autoId }!!
        viewModelScope.launch(Dispatchers.IO) {
            autoRepository.deleteAuto(auto = autoToDelete)
        }
    }

    fun setCurrentAutoById(autoId: Int) {
        currAuto.postValue(userAutos.value!!.find { it.id == autoId })
    }

    private fun getModelsByBrandId(brandId: Int): LiveData<List<Model>> {
        val brands = MutableLiveData<List<Model>>()
        viewModelScope.launch(Dispatchers.IO) {
            brands.postValue(autoRepository.getModelsByBrandId(brandId = brandId))
        }
        return brands
    }

    fun addNewAuto(
        brand: String,
        model: String,
        manufactureYear: Int,
        fuelType: String,
        registrationCertificateNumber: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            var brandId = getBrandIdByName(brand)
            if (brandId == null) {
                autoRepository.addBrand(Brand(id = 0, name = brand))
                brandId = getBrandIdByName(brand)
            }

            var modelId = getModelIdByName(model)
            if (modelId == null) {
                autoRepository.addModel(Model(id = 0, name = model, brandId = brandId!!))
                modelId = getModelIdByName(model)
            }

            var fuelTypeId = getFuelTypeIdByName(fuelType)
            if (fuelTypeId == null) {
                autoRepository.addFuelType(FuelType(id = 0, name = fuelType))
                fuelTypeId = getFuelTypeIdByName(fuelType)
            }

            val newAuto = Auto(
                id = 0,
                brandId = brandId!!,
                modelId = modelId!!,
                manufactureYear = manufactureYear,
                fuelTypeId = fuelTypeId!!,
                registrationCertificateNumber = registrationCertificateNumber
            )
            autoRepository.addAuto(newAuto)
//            currAuto.postValue(autoRepository.autos.value!!.find { it.modelId == modelId })
        }
    }

    fun addNewAuto(newAutoData: NewAutoData) {
        viewModelScope.launch(Dispatchers.IO) {
            val newAuto = Auto(
                id = 0,
                brandId = newAutoData.brandId,
                modelId = newAutoData.modelId,
                manufactureYear = newAutoData.manufactureYear,
                fuelTypeId = newAutoData.fuelTypeId,
                registrationCertificateNumber = newAutoData.registrationCertificateNumber
            )
            autoRepository.addAuto(newAuto)
        }
    }

    fun setNewName(newName: String) {
        currAuto.value!!.name = newName
    }

    fun setNewBrand(newBrandName: String) {
        var newBrand = getBrandIdByName(newBrandName)
        if (newBrand == null) {
            autoRepository.addBrand(Brand(id = 0, name = newBrandName))
            newBrand = getBrandIdByName(newBrandName)
        }
        currAuto.value!!.brandId = newBrand!!
    }

    fun setNewModel(newModelName: String) {
        var modelId = getModelIdByName(newModelName)
        if (modelId == null) {
            autoRepository.addModel(
                Model(
                    id = 0,
                    name = newModelName,
                    brandId = currAuto.value!!.brandId
                )
            )
            modelId = getModelIdByName(newModelName)
        }
        currAuto.value!!.modelId = modelId!!
    }

    fun setNewFuelType(newFuelTypeName: String) {
//        currentAuto.value!!.fuelTypeId = getFuelTypeIdByName(newFuelTypeName)
        var fuelTypeId = getFuelTypeIdByName(newFuelTypeName)
        if (fuelTypeId == null) {
            autoRepository.addFuelType(FuelType(id = 0, name = newFuelTypeName))
            fuelTypeId = getFuelTypeIdByName(newFuelTypeName)
        }
        currAuto.value!!.fuelTypeId = fuelTypeId!!
    }

    fun setNewRegistrationCertificate(newNumber: String) {
        currAuto.value!!.registrationCertificateNumber = newNumber
    }

//    private fun copyAutoObj(): Auto {
//        currentAuto.value!!.apply {
//            return Auto(
//                id,
//                name,
//                brandId,
//                modelId,
//                registrationNumber,
//                fuelTypeId,
//                lastMaintenanceDate,
//                insuranceExpirationDate,
//                registrationCertificateNumber
//            )
//        }
//    }


    private fun getBrandNameById(brandId: Int): String? =
        autoRepository.brands.find { it.id == brandId }?.name

    private fun getModelNameById(modelId: Int): String? =
        autoRepository.models.find { it.id == modelId }?.name

    private fun getFuelTypeNameById(fuelTypeId: Int): String? =
        autoRepository.fuelTypes.find { it.id == fuelTypeId }?.name

    private fun getBrandIdByName(brand: String): Int? =
        autoRepository.brands.find { it.name == brand }?.id

    private fun getModelIdByName(model: String): Int? =
        autoRepository.models.find { it.name == model }?.id

    private fun getFuelTypeIdByName(fuelType: String): Int? =
        autoRepository.fuelTypes.find { it.name == fuelType }?.id

    fun getAutosWithBrandAndModel(): List<AutoWithModelAndBrand> {
        val autos = mutableListOf<AutoWithModelAndBrand>()
        userAutos.value?.forEach { auto ->
            autos.add(
                AutoWithModelAndBrand(
                    auto.id,
                    auto.name,
                    getBrandNameById(auto.brandId)!!,
                    getModelNameById(auto.modelId)!!
                )
            )
        }
        return autos
    }

    fun didUserAddAuto(): Boolean = userAutos.value != null

    fun getBrandsNamesList(): List<String> {
        val list = mutableListOf<String>()
        brands.forEach {
            list.add(it.name)
        }
        return list
    }

    fun getModelsNamesList(): List<String> {
        val list = mutableListOf<String>()
        getModelsByBrandId(newAuto.brandId).value?.forEach {
            list.add(it.name)
        }
        return list
    }

    fun setNewAutoBrandName(brandName: String) {
        newAuto.brandId = getBrandIdByName(brandName)!!
    }

    fun setNewAutoModelName(modelName: String) {
        newAuto.modelId = getModelIdByName(modelName)!!
    }

    fun setNewAutoFuelTypeName(fuelTypeName: String) {
        newAuto.fuelTypeId = getFuelTypeIdByName(fuelTypeName)!!
    }

    fun setNewAutoRegNumber(registrationCertificateNumber: String) {
        newAuto.registrationCertificateNumber = registrationCertificateNumber
    }

    fun setNewAutoManufactureYear(manufactureYear: Int) {
        newAuto.manufactureYear = manufactureYear
    }

    fun buildAuto() {
        addNewAuto(newAuto)
    }

    fun createNewAuto() {
        newAuto = NewAutoData()
    }
}