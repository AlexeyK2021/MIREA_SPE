package ru.eco.automan.repositories

import androidx.lifecycle.LiveData
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
 * @param autoDao Dao для получения списка автомобилей пользователя
 * @param brandDao Dao для получения списка брендов автомобилей
 * @param modelDao Dao для получения списка моделей автомобилей конкретной марки
 * @param fuelTypeDao Dao для получения типов топлива автомобиля
 */
class AutoRepository(
    private val autoDao: AutoDao,
    private val brandDao: BrandDao,
    private val modelDao: ModelDao,
    private val fuelTypeDao: FuelTypeDao
) {
    private var _autos: LiveData<List<Auto>> = autoDao.getAllAutosLiveData()
    private var _brands: List<Brand> = brandDao.getAllBrands()

    val autos get() = _autos
    val brands get() = _brands

    fun addAuto(auto: Auto) = autoDao.addAuto(auto)

    fun updateAuto(auto: Auto) = autoDao.updateAuto(auto)

    fun deleteAuto(auto: Auto) = autoDao.deleteAuto(auto)

    fun getModelsByBrand(brand: Brand) = modelDao.getModelsByBrandId(brandId = brand.id)
}
