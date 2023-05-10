package ru.eco.automan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.models.Event
import ru.eco.automan.viewModels.getEstimatedDays

/**
 * ViewHolder для списка предстоящих событий.
 */
class EventViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val eventName: TextView = itemview.findViewById(R.id.event_name)
    val eventDate: TextView = itemview.findViewById(R.id.days_before)
}

/**
 * Адаптер для списка предстоящих событий.
 * @param eventsList список предстоящих событий
 */
class EventsAdapter(private val eventsList: List<Event>) : RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.event_recycle_item, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val curr = eventsList[position]
        val daysBefore = curr.date.getEstimatedDays()

        val days = holder.itemView.resources.getQuantityString(
            R.plurals.days, daysBefore, daysBefore
        )

        holder.apply {
            eventName.text = curr.name
            eventDate.text = days
        }
    }

    override fun getItemCount(): Int = eventsList.size
}