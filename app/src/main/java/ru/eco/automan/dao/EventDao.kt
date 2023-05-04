package ru.eco.automan.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import ru.eco.automan.models.Event

/**
 * Интерфейс, позволяющий получить доступ к данным о предстоящих собитиях
 * @see Event
 */
interface EventDao {

    @Insert
    fun addEvent(event: Event)

    @Query("DELETE FROM event WHERE id =:eventId")
    fun deleteEvent(eventId: Int)

    @Query("SELECT * FROM event")
    fun getAllEventsLiveData(): LiveData<List<Event>>

}