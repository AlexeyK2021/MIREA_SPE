package ru.eco.automan.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.AutoApplication
import ru.eco.automan.dao.AutoDao
import ru.eco.automan.dao.CategoryDao
import ru.eco.automan.models.Auto
import ru.eco.automan.models.Category

class CategoryRepository : ViewModel() {
    private var _categories: LiveData<List<Category>>
    val categories get() = _categories.value
    private var categoryDao: CategoryDao? = null

    init {
        categoryDao = AutoApplication.database.categoryDao()
        _categories = categoryDao?.getAllCategoriesLiveData()!!
    }

    fun addCategory(category: Category) =
        viewModelScope.launch(Dispatchers.IO) { categoryDao?.addCategory(category) }

    fun updateCategory(category: Category) =
        viewModelScope.launch(Dispatchers.IO) { categoryDao?.updateCategory(category)!! }

    fun deleteCategory(category: Category) =
        viewModelScope.launch(Dispatchers.IO) { categoryDao?.deleteCategory(category)!! }
}