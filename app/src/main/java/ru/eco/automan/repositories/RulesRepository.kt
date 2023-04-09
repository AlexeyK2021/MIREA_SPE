package ru.eco.automan.repositories

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
    private var allChapters: List<Chapter> = chapterDao.getAllChapters()
    val chapters get() = allChapters

    fun getParagraphsByChapter(chapter: Chapter): List<Paragraph> =
        paragraphDao.getParagraphsByChapterId(chapterId = chapter.id)
}