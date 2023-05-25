package ru.eco.automan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.fragments.DeleteDialogFragment
import ru.eco.automan.listeners.OnAutoChooseClickListener
import ru.eco.automan.listeners.OnDialogListener
import ru.eco.automan.models.AutoWithModelAndBrand

/**
 * ViewHolder для каждого автомобиля автомобиля
 */
class ChooseAutoViewHolder(
    itemView: View,
    private val onAutoChooseClickListener: OnAutoChooseClickListener
) : RecyclerView.ViewHolder(itemView), OnDialogListener {
    private val autoName: TextView = itemView.findViewById(R.id.event_name)

    private val deleteAuto: ImageView = itemView.findViewById(R.id.delete_auto)
    private var autoId = 0
    fun bind(currAuto: AutoWithModelAndBrand) {
        autoId = currAuto.id
        var name = currAuto.name
        if (name == null) name = "${currAuto.brandName} ${currAuto.modelName}"
        autoName.text = name

        deleteAuto.setOnClickListener {
//            DeleteDialogFragment(R.string.delete_auto_label, R.string.delete_auto, this, itemView.context)
            onAutoChooseClickListener.onDeleteClick(autoId)
        }

        itemView.setOnClickListener { onAutoChooseClickListener.onChooseClick(currAuto.id) }
    }

    override fun onNegativeButtonClicked() {
    }

    override fun onPositiveButtonClicked() {
        onAutoChooseClickListener.onDeleteClick(autoId)
    }
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
        return ChooseAutoViewHolder(itemView, onAutoChooseClickListener)
    }

    override fun onBindViewHolder(holder: ChooseAutoViewHolder, position: Int) {
        val currAuto = autosList[position]
        holder.bind(currAuto)
    }

    override fun getItemCount(): Int = autosList.size
}


