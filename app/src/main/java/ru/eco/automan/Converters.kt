package ru.eco.automan

import androidx.room.TypeConverter
import java.sql.Date

/**
 * Класс конверторов даты из Date в Int и наоборот для дальнейшего сохранения в БД
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}