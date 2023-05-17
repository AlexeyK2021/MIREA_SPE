package ru.eco.automan.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.listeners.OnChangeExpenseDataRequestListener
import ru.eco.automan.models.Expense
import java.util.Currency
import java.util.Locale

/**
 * Функция для получения для получения денежных затрат на трату.
 */
fun Float.currency(): String =
    this.toString() + Currency.getInstance(Locale.getDefault(Locale.Category.DISPLAY)).symbol

/**
 * ViewHolder для каждой траты в определенной категории
 */
class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val wasteName: TextView = view.findViewById(R.id.nameWastes)
    val wasteCost: TextView = view.findViewById(R.id.costWastes)
}

/**
 *  Адаптер для траты в определенной категории
 *  @param expenses список трат в определенной категории
 */
class ExpenseAdapter(
    private val expenses: List<Expense>,
    private val onChangeExpenseDataRequestListener: OnChangeExpenseDataRequestListener
) :
    RecyclerView.Adapter<ExpenseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.wastes_card_recycler_item, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.apply {
            wasteName.text = expense.name
            wasteCost.text = expense.amount.currency()

            itemView.setOnClickListener {
                onChangeExpenseDataRequestListener.onChangeDataRequest(
                    expenseId = expense.id,
                    expenseName = expense.name,
                    expenseAmount = expense.amount
                )
            }
        }
    }

    override fun getItemCount(): Int = expenses.size
}
