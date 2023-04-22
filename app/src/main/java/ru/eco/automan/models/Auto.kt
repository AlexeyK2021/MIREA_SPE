package ru.eco.automan.models

import androidx.room.*
import java.sql.Date

/**
 * Класс данных автомобиля
 * @param id ID-номер автомобиля
 * @param name Имя (псевдоним) автомобиля, которое задаёт пользователь
 * @param brandId ID-номер бренда автомобиля
 * @param modelId ID-номер модели автомобиля
 * @param registrationNumber Регистрационный номер автомобиля
 * @param fuelTypeId ID-номер типа топлива автомобиля
 * @param lastMaintenanceDate Дата последнего технического обслуживания
 * @param insuranceExpirationDate Дата окнончания действия страхования автомобиля
 * @param registrationCertificateNumber Номер свидетельства о регистрации транспортного средства
 * @param manufactureYear Год выпуска автомобиля
 */
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
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo var name: String? = null,
    @ColumnInfo(name = "brand_id") var brandId: Int,
    @ColumnInfo(name = "model_id") var modelId: Int,
    @ColumnInfo(name = "registration_number") var registrationNumber: String? = null,
    @ColumnInfo(name = "fuel_type_id") var fuelTypeId: Int,
    @ColumnInfo(name = "last_maintenance_date") var lastMaintenanceDate: Date? = null,
    @ColumnInfo(name = "insurance_expiration_date") var insuranceExpirationDate: Date? = null,
    @ColumnInfo(name = "registration_certificate_number") var registrationCertificateNumber: String? = null,
    @ColumnInfo(name = "manufacture_year") var manufactureYear: Int
)