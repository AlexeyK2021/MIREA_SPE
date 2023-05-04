package ru.eco.automan.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.eco.automan.dao.ChapterDao
import ru.eco.automan.dao.ParagraphDao
import ru.eco.automan.models.*

/**
 * Класс-репозиторий, хранящий главы и пункты правил ПДД
 * @see Chapter
 * @see Paragraph
 * @param chapterDao Dao для получения глав ПДД
 * @param paragraphDao Dao для получения пунктов ПДД
 */
class RulesRepository(
    private val chapterDao: ChapterDao,
    private val paragraphDao: ParagraphDao
) {
    private var allChapters: List<Chapter>? = null
    val chapters get() = allChapters

    private var allParagraphs: List<Paragraph>? = null
    val paragraphs get() = allParagraphs

    init {
        runBlocking(Dispatchers.IO) {
            val chaptersJob = launch {
                allChapters = chapterDao.getAllChapters()
            }
            val paragraphsJob = launch {
                allParagraphs = paragraphDao.getAllParagraphs()
            }
            chaptersJob.join()
            paragraphsJob.join()
        }
    }

    /**
     * Метод получения пунктов Правил Дорожного Движения по экземпляру главы
     * @param chapter Экземпляр главы, для которой необходимо получить список пунктов
     * @return Список пунктов правил по определенной главе
     */
    fun getParagraphsByChapterId(chapterId: Int): List<Paragraph> {
        val paragraphsList = mutableListOf<Paragraph>()
        allParagraphs!!.forEach {
            if (it.chapterId == chapterId) paragraphsList.add(it)
        }
        return paragraphsList
    }

}