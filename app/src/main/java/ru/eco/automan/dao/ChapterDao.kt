package ru.eco.automan.dao

import androidx.room.Query
import ru.eco.automan.models.Chapter

interface ChapterDao {

    @Query("SELECT * FROM chapter")
    fun getAllChapters(): List<Chapter>
}