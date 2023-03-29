package ru.eco.automan.models

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import java.sql.Date


@Entity(
    tableName = "auto",
    foreignKeys = [
        ForeignKey(
            entity = EngineType::class,
            parentColumns = ["id"],
            childColumns = ["engine_type_id"]
        ),
        ForeignKey(
            entity = Brand::class,
            parentColumns = ["id"],
            childColumns = ["brand_id"]
        ),
        ForeignKey(
            entity = Model::class,
            parentColumns = ["id"],
            childColumns = ["model_id"]
        )
    ]
)
data class Auto(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo(name = "brand_id") val brandId: String,
    @ColumnInfo(name = "model_id") val modelId: String,
    @ColumnInfo(name = "registration_number") val registrationNumber: String,
    @ColumnInfo(name = "engine_type_id") val engineTypeId: Int,
    @ColumnInfo(name = "last_maintenance_date") val lastMaintenanceDate: Date,
    @ColumnInfo(name = "insurance_expiration_date") val insuranceExpirationDate: Date
)