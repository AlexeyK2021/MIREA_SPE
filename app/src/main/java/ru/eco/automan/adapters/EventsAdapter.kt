package ru.eco.automan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.AutoApplication
import ru.eco.automan.R
import ru.eco.automan.models.Event
import java.util.Calendar
import java.util.Date

class EventViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val eventName: TextView = itemview.findViewById(R.id.event_name)
    val eventDate: TextView = itemview.findViewById(R.id.days_before)
}

class EventsAdapter(private val eventsList: List<Event>) : RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.event_recycle_item, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val curr = eventsList[position]
        val days = holder.itemView.resources.getQuantityString(
            R.plurals.days,
            getNumberDaysBefore(curr.date)
        )
        holder.apply {
            eventName.text = curr.name
            eventDate.text = days
        }
    }

    override fun getItemCount(): Int = eventsList.size

    private fun getNumberDaysBefore(date: Date): Int =
        ((Calendar.getInstance().timeInMillis - date.time) / AutoApplication.periods[0].secondsNum).toInt()

}