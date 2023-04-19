package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Paragraph
/**
 * Интерфейс, позволяющий получить доступ к пунктам правил ПДД, хранящихся в базе данных
 * @see Paragraph
 */
@Dao
interface ParagraphDao {
    /**
     * Метод для получения списка всех пунктов Правил Дорожного Движения
     * @return Список всех пунктов
     */
    @Query("SELECT * FROM paragraph")
    fun getAllParagraphs(): List<Paragraph>

    /**
     * Метод для получения пунктов ПДД по ID-номеру главы
     * @param chapterId ID-номер главы
     * @return Список пунктов входящих в эту главу
     */
    @Query("SELECT * FROM paragraph WHERE chapter_id=:chapterId")
    fun getParagraphsByChapterId(chapterId: Int): List<Paragraph>
}