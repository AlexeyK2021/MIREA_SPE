package ru.eco.automan.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.models.Expense

class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {//ViewHolder для каждой категории трат

}

class ExpenseAdapter(private val expenses: List<Expense>) :
    RecyclerView.Adapter<ExpenseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.apply {

        }
    }

    override fun getItemCount(): Int = expenses.size
}