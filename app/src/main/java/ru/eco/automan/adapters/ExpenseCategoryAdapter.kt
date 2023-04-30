package ru.eco.automan.adapters

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
}

class ExpenseCategoryAdapter(
    private val data: List<CategoryWithExpenseAndIcon>,
    private var onAddExpenseListener: OnAddExpenseListener
) :
    RecyclerView.Adapter<ExpenseCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.wastes_recycle_item, parent, false)
        return ExpenseCategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseCategoryViewHolder, position: Int) {
        val expenses = data[position].expensesList
        val image = data[position].categoryImage
        val name = data[position].categoryName
        val sum = data[position].wastesSum

        holder.apply {
            wasteImage.setImageDrawable(image)
            wasteCategoryName.text = name
            wastesSum.text = sum.currency()

            wastesListRecycler.adapter = ExpenseAdapter(expenses)
            wastesListRecycler.layoutManager = LinearLayoutManager(holder.itemView.context)

            addWaste.setOnClickListener {
                onAddExpenseListener.addExpense(
                    categoryName = name,
                    expenseAmount = newWasteAmount.text.toString().toFloat(),
                    expenseName = newWasteName.text.toString()
                )
                newWasteName.text = ""
                newWasteAmount.text = ""
            }
        }
    }

    override fun getItemCount(): Int = data.size
}