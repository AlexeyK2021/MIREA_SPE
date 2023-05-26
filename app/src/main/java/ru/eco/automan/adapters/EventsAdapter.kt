package ru.eco.automan.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.listeners.EventActionListener
import ru.eco.automan.models.Event
import ru.eco.automan.viewModels.getEstimatedDays
import java.sql.Date

/**
 * ViewHolder для списка предстоящих событий.
 */
class EventViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val eventName: TextView = itemview.findViewById(R.id.event_name)
    val eventDate: TextView = itemview.findViewById(R.id.days_before)
    val eventDateDescription: TextView = itemview.findViewById(R.id.event_description)
    val editButton: TextView = itemview.findViewById(R.id.edit_button)
    val deleteButton: TextView = itemview.findViewById(R.id.delete_button)
    val enterData: ConstraintLayout = itemview.findViewById(R.id.enter_event_data)
    val confirmButton: ImageView = itemview.findViewById(R.id.confirm_button)
    val cancelButton: ImageView = itemview.findViewById(R.id.cancel_button)
    val calendarImage: ImageView = itemview.findViewById(R.id.calendar_image)
    val newEventName: EditText = itemview.findViewById(R.id.new_event)
    val newEventDate: TextView = itemview.findViewById(R.id.event_date)
    val eventFragment: ConstraintLayout = itemview.findViewById(R.id.event_fragment)
}

/**
 * Адаптер для списка предстоящих событий.
 * @param eventsList список предстоящих событий
 */
class EventsAdapter(private val eventsList: List<Event>) : RecyclerView.Adapter<EventViewHolder>() {

    //var isExpand: Boolean = false
    val isExpandList: MutableList<Boolean> = mutableListOf()
    lateinit var eventActionListener: EventActionListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.event_recycle_item, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        isExpandList.add(position, false)

        val curr = eventsList[position]
        val daysBefore = curr.date.getEstimatedDays()
        val dateList = getSeparatedDate(curr.date)
        val day = dateList[2]
        val month = getMonthNameById(dateList[1], holder.itemView.context)
        val year = dateList[0]
        val description = "Следующая дата события\n- $day $month $year года, осталось:"

        val days = holder.itemView.resources.getQuantityString(
            R.plurals.days, daysBefore, daysBefore
        )

        holder.apply {
            eventName.text = curr.name
            eventDate.text = days
            eventDateDescription.text = description
            //
            itemView.setOnClickListener {
                eventActionListener.onEventClick(holder)
            }
            deleteButton.setOnClickListener {
                eventActionListener.onDeleteClick(curr)
            }
            editButton.setOnClickListener {
                eventActionListener.onEditClick(holder, curr)
            }
            confirmButton.setOnClickListener {
                eventActionListener.onConfirmButtonClick(holder, curr)
            }
            cancelButton.setOnClickListener {
                eventActionListener.onCancelButtonClick(holder)
            }
            calendarImage.setOnClickListener {
                eventActionListener.onCalendarImageClick(holder)
            }
        }
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }
}

/**
 * Эта функция переводит числовое значение месяца в буквенное
 *
 * @param monthId: число от 1 до 12
 * @return строку с названием месяца в родительном падеже
 */
fun getMonthNameById(monthId: Int, context: Context): String {
//    return when(dataTime.month) {
//        Month.JANUARY -> "января"
//        Month.FEBRUARY -> "февраля"
//        Month.MARCH -> "марта"
//        Month.APRIL -> "апреля"
//        Month.MAY -> "мая"
//        Month.JUNE -> "июня"
//        Month.JULY -> "июля"
//        Month.AUGUST -> "августа"
//        Month.SEPTEMBER -> "сентября"
//        Month.OCTOBER -> "октября"
//        Month.NOVEMBER -> "ноября"
//        Month.DECEMBER -> "декабря"
//        else -> "ERROR"
//    }
    return context.resources.getStringArray(R.array.months)[monthId - 1].lowercase()
}

/**
 * Эта функция возвращает список из трёх элементов: день, месяц, год в числовом формате
 * доставая из передаваемого внутрь значения date
 *
 * @param date (тип sql.Date): дата
 * @return список [год, месяц, день]
 */
fun getSeparatedDate(date: Date): List<Int> {
    val strDate = date.toString() // 2023-05-22
    val year = strDate.subSequence(0, 4).toString().toInt()
    Log.d("frog", "year: $year")
    val month = strDate.subSequence(5, 7).toString().toInt()
    Log.d("frog", "month: $month")
    val day = strDate.subSequence(8, 10).toString().toInt()
    Log.d("frog", "day: $day")

    return listOf(year, month, day)
}
