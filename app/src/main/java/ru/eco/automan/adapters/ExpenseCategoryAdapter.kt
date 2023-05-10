package ru.eco.automan.adapters

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.os.Build
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import ru.eco.automan.R
import ru.eco.automan.listeners.OnAddExpenseListener
import ru.eco.automan.models.CategoryWithExpenseAndIcon

/**
 * ViewHolder для списка категорий трат
 */
class ExpenseCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintWastes)
    val cardView: CardView = view.findViewById(R.id.CardView)
    val wasteImage: ImageView = view.findViewById(R.id.imageWastes)
    val wasteCategoryName: TextView = view.findViewById(R.id.textWastes)
    val wastesSum: TextView = view.findViewById(R.id.costWastes)
    val wastesListRecycler: RecyclerView = view.findViewById(R.id.recycler_wastes)
    val newWasteName: TextView = view.findViewById(R.id.new_event)
    val newWasteAmount: TextView = view.findViewById(R.id.newWasteAmount)
    val addWaste: ImageView = view.findViewById(R.id.add_event)

    fun bind(result: CategoryWithExpenseAndIcon, onAddExpenseListener: OnAddExpenseListener) {
        wasteImage.setImageDrawable(result.categoryImage)
        wasteCategoryName.text = result.categoryName
        wastesSum.text = result.wastesSum.currency()

        wastesListRecycler.adapter = ExpenseAdapter(result.expensesList)
        wastesListRecycler.layoutManager = LinearLayoutManager(itemView.context)

        constraintLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        addWaste.setOnClickListener {
            val expenseAmount = newWasteAmount.text.toString()
            if (newWasteName.text.isNotEmpty())
                onAddExpenseListener.addExpense(
                    categoryName = result.categoryName,
                    expenseAmount = if (expenseAmount.isEmpty()) 0f else expenseAmount.toFloat(),
                    expenseName = newWasteName.text.toString()
                )
            newWasteName.text = ""
            newWasteAmount.text = ""
        }

        cardView.setOnClickListener {
            expend()
        }
    }

    fun expend(){
        val v: Int = if (wastesListRecycler.visibility == View.GONE &&
            addWaste.visibility == View.GONE &&
            newWasteName.visibility == View.GONE &&
            newWasteAmount.visibility == View.GONE) {
            View.VISIBLE
        } else {
            View.GONE
        }

        TransitionManager.beginDelayedTransition(constraintLayout, AutoTransition())
        addWaste.visibility = v
        newWasteName.visibility = v
        newWasteAmount.visibility = v
        wastesListRecycler.visibility = v
    }
}

/**
 * Адаптер для отображения категорий трат.
 * @param data данные для отображения списка категорий.
 * @param onAddExpenseListener интерфейс для добавления траты.
 */
class ExpenseCategoryAdapter(
    private var data: List<CategoryWithExpenseAndIcon>,
    private var onAddExpenseListener: OnAddExpenseListener
) :
    RecyclerView.Adapter<ExpenseCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.wastes_recycle_item, parent, false)
        return ExpenseCategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseCategoryViewHolder, position: Int) {
        holder.bind(result = data[position], onAddExpenseListener = onAddExpenseListener)
        holder.expend()
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<CategoryWithExpenseAndIcon>) {
        data = newData
        notifyDataSetChanged()
    }
}