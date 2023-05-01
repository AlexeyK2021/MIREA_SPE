package ru.eco.automan.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.listeners.OnAddExpenseListener
import ru.eco.automan.models.CategoryWithExpenseAndIcon

/**
 * ViewHolder для списка категорий трат
 */
class ExpenseCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val wasteImage: ImageView = view.findViewById(R.id.imageWastes)
    val wasteCategoryName: TextView = view.findViewById(R.id.textWastes)
    val wastesSum: TextView = view.findViewById(R.id.costWastes)
    val wastesListRecycler: RecyclerView = view.findViewById(R.id.recycler_wastes)
    val newWasteName: TextView = view.findViewById(R.id.newWasteName)
    val newWasteAmount: TextView = view.findViewById(R.id.newWasteAmount)
    val addWaste: ImageView = view.findViewById(R.id.add_waste)

    fun bind(result: CategoryWithExpenseAndIcon, onAddExpenseListener: OnAddExpenseListener) {
        wasteImage.setImageDrawable(result.categoryImage)
        wasteCategoryName.text = result.categoryName
        wastesSum.text = result.wastesSum.currency()

        wastesListRecycler.adapter = ExpenseAdapter(result.expensesList)
        wastesListRecycler.layoutManager = LinearLayoutManager(itemView.context)

        addWaste.setOnClickListener {
            onAddExpenseListener.addExpense(
                categoryName = result.categoryName,
                expenseAmount = newWasteAmount.text.toString().toFloat(),
                expenseName = newWasteName.text.toString()
            )
            newWasteName.text = ""
            newWasteAmount.text = ""
        }
    }
}

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
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<CategoryWithExpenseAndIcon>) {
        data = newData
        notifyDataSetChanged()
    }
}