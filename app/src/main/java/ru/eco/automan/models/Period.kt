package ru.eco.automan.models

/**
 * Класс для хранения временных промежутков
 * @param name имя временного промежутка
 * @param secondsNum количество секунд в этом промежутке
 */
data class Period(val name: String, val secondsNum: Long)