package ru.eco.automan.models

import androidx.room.*
import java.sql.Date


@Entity(
    tableName = "auto",
    foreignKeys = [
        ForeignKey(
            entity = FuelType::class,
            parentColumns = ["id"],
            childColumns = ["fuel_type_id"]
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
    @ColumnInfo val name: String?,
    @ColumnInfo(name = "brand_id") val brandId: Int,
    @ColumnInfo(name = "model_id") val modelId: Int,
    @ColumnInfo(name = "registration_number") val registrationNumber: String?,
    @ColumnInfo(name = "fuel_type_id") val fuelTypeId: Int,
    @ColumnInfo(name = "last_maintenance_date",) val lastMaintenanceDate: Date?,
    @ColumnInfo(name = "insurance_expiration_date") val insuranceExpirationDate: Date?
)