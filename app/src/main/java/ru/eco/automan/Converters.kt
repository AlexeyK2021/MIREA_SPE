package ru.eco.automan

import androidx.room.TypeConverter
import java.sql.Date

/**
 * Класс конверторов даты из Date в Long и наоборот для дальнейшего сохранения в БД
 */
class Converters {
    /**
     * Превращение даты из типа снимка (хранится в БД) в тим Date для работы с ним в программе
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Превращение даты из типа Date в тип снимка для хранения в БД
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}