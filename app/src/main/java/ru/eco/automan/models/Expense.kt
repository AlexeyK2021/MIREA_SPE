package ru.eco.automan.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    tableName = "expense",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"]
        ),
        ForeignKey(
            entity = Auto::class,
            parentColumns = ["id"],
            childColumns = ["auto_id"]
        )
    ]
)
data class Expense(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val amount: Float,
    @ColumnInfo val date: Date,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo(name = "auto_id") val autoId: Int
)
