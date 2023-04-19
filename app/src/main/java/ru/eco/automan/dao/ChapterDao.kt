package ru.eco.automan.dao

import androidx.room.Dao
import androidx.room.Query
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Chapter
/**
 * Интерфейс, позволяющий получить доступ к главам правил ПДД, хранящихся в базе данных
 * @see Chapter
 */
@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapter")
    fun getAllChapters(): List<Chapter>
}