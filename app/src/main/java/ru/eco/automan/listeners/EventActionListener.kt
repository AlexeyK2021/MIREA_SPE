package ru.eco.automan.listeners

import ru.eco.automan.adapters.EventViewHolder
import ru.eco.automan.models.Event

interface EventActionListener {
    fun onDeleteClick(curr: Event)

    fun onEditClick(holder: EventViewHolder, curr: Event)

    fun onEventClick(holder: EventViewHolder)

    fun onConfirmButtonClick(holder: EventViewHolder, curr: Event)

    fun onCancelButtonClick(holder: EventViewHolder)

    fun onCalendarImageClick(holder: EventViewHolder)
}