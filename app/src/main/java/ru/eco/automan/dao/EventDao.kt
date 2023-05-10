package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.eco.automan.models.Event

/**
 * Интерфейс, позволяющий получить доступ к данным о предстоящих собитиях
 * @see Event
 */
@Dao
interface EventDao {
    /**
     * Метод добавления нового предстоящего события в базу данных
     * @param event экземпляр нового предстоящего события
     */
    @Insert
    fun addEvent(event: Event)

    /**
     * Метод получения списка предстоящих событий
     * @return список всех событий.
     */
    @Query("SELECT * FROM event")
    fun getAllEvents(): List<Event>

    /**
     * Метод получения списка всех ивентов в виде LiveData
     * @return список всех предстоящих событий в формате LiveData
     */
    @Query("SELECT * FROM event")
    fun getAllEventsLiveData(): LiveData<List<Event>>

    /**
     * метод удаления предстоящего события по его ID-номеру
     * @param eventId ID-номер события
     */
    @Query("DELETE FROM event WHERE id =:eventId")
    fun deleteEvent(eventId: Int)

}