package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "chapter_id") val idChapter: Int,
    @ColumnInfo val text: String?,
    @ColumnInfo val name: String?
)
