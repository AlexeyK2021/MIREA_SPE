package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Класс данных пункта Правил Дорожного Движения
 * @param id ID-номер пункта правил
 * @param chapterId ID-номер главы, к которой принадлежит пункт
 * @param text Текстовое содержимое пункта
 * @param name Имя пункта
 */
@Entity(
    tableName = "paragraph",
    foreignKeys = [
        ForeignKey(
            entity = Chapter::class,
            parentColumns = ["id"],
            childColumns = ["chapter_id"]
        )
    ]
)
data class Paragraph(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "chapter_id") val chapterId: Int,
    @ColumnInfo val text: String?,
    @ColumnInfo val name: String?
)
