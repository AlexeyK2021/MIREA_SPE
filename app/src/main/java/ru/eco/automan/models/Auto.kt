package ru.eco.automan.models

import androidx.room.*


@Entity(
//    foreignKeys = arrayOf(
//        ForeignKey(
//            EngineType::class,
//            ["id"],
//            ["engineTypeId"]
//        )
//    )
)
data class Auto(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val brand: String,
    @ColumnInfo val model: String,
    @ColumnInfo val registrationNumber: String,
    @Embedded val engineTypeId: EngineType

)