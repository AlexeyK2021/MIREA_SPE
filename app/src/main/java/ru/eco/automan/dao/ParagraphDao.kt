package ru.eco.automan.dao

import androidx.room.Query
import ru.eco.automan.models.Brand
import ru.eco.automan.models.Paragraph

interface ParagraphDao {

    @Query("SELECT * FROM paragraph")
    fun getAllParagraphs(): List<Paragraph>
}