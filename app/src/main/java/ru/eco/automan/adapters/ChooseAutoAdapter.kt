package ru.eco.automan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.listeners.OnAutoChooseClickListener
import ru.eco.automan.models.AutoWithModelAndBrand

/**
 * ViewHolder для каждого автомобиля автомобиля
 */
class ChooseAutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val autoName: TextView = itemView.findViewById(R.id.event_name)
    val deleteAuto: ImageView = itemView.findViewById(R.id.delete_auto)
}

/**
 * Адаптер для выбора автомобилей из списка
 * @param autosList список автомобилей и пользователя
 * @param onAutoChooseClickListener интерфейс, определяющий реакцию по нажатию на автомобиль
 */
class ChooseAutoAdapter(
    private val autosList: List<AutoWithModelAndBrand>,
    private val onAutoChooseClickListener: OnAutoChooseClickListener
) :
    RecyclerView.Adapter<ChooseAutoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseAutoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.add_recycle_item, parent, false)
        return ChooseAutoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChooseAutoViewHolder, position: Int) {
        val currAuto = autosList[position]
        holder.apply {
            var name = currAuto.name
            if (name == null) name = "${currAuto.brandName} ${currAuto.modelName}"
            autoName.text = name

            deleteAuto.setOnClickListener {
                onAutoChooseClickListener.onDeleteClick(currAuto.id)
            }
            holder.itemView.setOnClickListener { onAutoChooseClickListener.onChooseClick(currAuto.id) }
        }
    }

    override fun getItemCount(): Int = autosList.size
}


