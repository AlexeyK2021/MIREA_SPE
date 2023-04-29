package ru.eco.automan.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import ru.eco.automan.dao.AutoDao
import ru.eco.automan.dao.BrandDao
import ru.eco.automan.dao.FuelTypeDao
import ru.eco.automan.dao.ModelDao
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Brand
import ru.eco.automan.models.FuelType
import ru.eco.automan.models.Model

/**
 * Пограничный класс-репозиторий, работающий с данными в базе данных и автомобилями пользователя, списками марок и соответствующими им моделями автомобилей
 * @see Auto
 * @see Brand
 * @see Model
 * @see FuelType
 * @param autoDao Dao для манипулирования данными об автомобилях пользователя
 * @param brandDao Dao для манипулирования данными о брендах автомобилей
 * @param modelDao Dao для манипулирования данными об автомобилах конкретной марки
 * @param fuelTypeDao Dao для манипулирования данными о типах топлива автомобиля
 */
class AutoRepository(
    private val autoDao: AutoDao,
    private val brandDao: BrandDao,
    private val modelDao: ModelDao,
    private val fuelTypeDao: FuelTypeDao
) {
    private var _autos: LiveData<List<Auto>>? = null
    private var _brands: List<Brand>? = null
    private var _models: List<Model>? = null
    private var _fuels: List<FuelType>? = null

    val autos get() = _autos!!
    val brands get() = _brands!!
    val models get() = _models!!
    val fuelTypes get() = _fuels!!

    init {
        runBlocking(Dispatchers.IO){
            _autos = autoDao.getAllAutosLiveData()
            _brands = brandDao.getAllBrands()
            _models = modelDao.getAllModels()
            _fuels = fuelTypeDao.getAllFuelTypes()
        }
    }
    /**
     * Метод добавления нового автомобиля пользователя в БД
     * @param auto Экземпляр автомобиля с заполненными полями данных для внесения в БД
     */
    fun addAuto(auto: Auto) = autoDao.addAuto(auto)

    /**
     * Метод обновления данных о существующем в БД автомобиле пользователя
     * @param auto Экземпляр автомобиля с новыми данными
     */
    fun updateAuto(auto: Auto) = autoDao.updateAuto(auto)

    /**
     * Метод удаления существующего в БД автомобиля
     * @param auto Экземпляр автомобиля, подлежащего удалению
     */
    fun deleteAuto(auto: Auto) = autoDao.deleteAuto(auto)

    /**
     * Метод получения списка моделей по определенному бренду
     * @param brand Экземпляр бренда, для которого необходимо получить список моделей
     * @return Список моделей по переданному экземпляру бренда
     */
    fun getModelsByBrandId(brandId: Int) = modelDao.getModelsByBrandId(brandId = brandId)

    fun addBrand(brand: Brand) {
        brandDao.addBrand(brand)
        _brands = brandDao.getAllBrands()
    }

    fun addModel(model: Model) {
        modelDao.addModel(model)
        _models = modelDao.getAllModels()
    }

    fun addFuelType(fuelType: FuelType) {
        fuelTypeDao.addFuelType(fuelType)
        _fuels = fuelTypeDao.getAllFuelTypes()
    }

}
