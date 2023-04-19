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

    @Query("SELECT * FROM paragraph")
    fun getAllParagraphs(): List<Paragraph>

    @Query("SELECT * FROM paragraph WHERE chapter_id=:chapterId")
    fun getParagraphsByChapterId(chapterId: Int): List<Paragraph>
}