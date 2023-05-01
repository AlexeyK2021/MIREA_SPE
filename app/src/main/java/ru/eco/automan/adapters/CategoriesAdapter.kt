package ru.eco.automan.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.models.Category
import ru.eco.automan.models.Expense

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}

class CategoriesAdapter(private val expenses: Map<Category, List<Expense>>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {

        }
    }

    override fun getItemCount(): Int = expenses.keys.size
}