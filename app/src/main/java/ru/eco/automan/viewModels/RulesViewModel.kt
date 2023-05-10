package ru.eco.automan.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eco.automan.models.Chapter
import ru.eco.automan.models.Paragraph
import ru.eco.automan.repositories.RulesRepository

/**
 * ViewModel, отвечающая за работу с правилами ПДД.
 * @param rulesRepository класс репозиторий, работающий с базой данных
 */
class RulesViewModel(private val rulesRepository: RulesRepository) : ViewModel() {
    var currentChapterId = MutableLiveData<Int>()

    val chapters get() = rulesRepository.chapters

    fun getAllChapters(): List<Chapter> {
        var rules = mutableListOf<Chapter>()
        viewModelScope.launch(Dispatchers.IO) {
            rules = rulesRepository.chapters?.toMutableList()!!
        }
        while (rules.size == 0) {
        }
        return rules
    }

    fun getCurrentChapterName(): String = getChapterNameById(currentChapterId.value!!)

    private fun getChapterNameById(chapterId: Int): String =
        chapters?.find { it.id == chapterId }?.name!!

    fun getParagraphsForCurrentChapter(): List<Paragraph> =
        rulesRepository.getParagraphsByChapterId(currentChapterId.value!!)

}