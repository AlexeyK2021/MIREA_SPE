package ru.eco.automan.adapters

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.fragments.DeleteDialogFragment
import ru.eco.automan.listeners.OnAddExpenseListener
import ru.eco.automan.listeners.OnChangeExpenseDataRequestListener
import ru.eco.automan.listeners.OnChangeExpenseListener
import ru.eco.automan.listeners.OnDialogListener
import ru.eco.automan.models.CategoryWithExpenseAndIcon

/**
 * ViewHolder для списка категорий трат
 */
class ExpenseCategoryViewHolder(
    view: View,
    private val onChangeExpenseListener: OnChangeExpenseListener
) :
    RecyclerView.ViewHolder(view), OnChangeExpenseDataRequestListener, OnDialogListener {
    private val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintWastes)
    private val cardView: CardView = view.findViewById(R.id.CardView)
    private val wasteImage: ImageView = view.findViewById(R.id.imageWastes)
    private val wasteCategoryName: TextView = view.findViewById(R.id.textWastes)
    private val wastesSum: TextView = view.findViewById(R.id.costWastes)
    private val wastesListRecycler: RecyclerView = view.findViewById(R.id.recycler_wastes)
    private val newWasteName: TextView = view.findViewById(R.id.new_expense)
    private val newWasteAmount: TextView = view.findViewById(R.id.newWasteAmount)
    private val addWaste: ImageView = view.findViewById(R.id.add_event)
    private val deleteEditing: ImageView = view.findViewById(R.id.delete_expense)

    private var editingExpenseId = 0

    fun bind(result: CategoryWithExpenseAndIcon, onAddExpenseListener: OnAddExpenseListener) {
        wasteImage.setImageDrawable(result.categoryImage)
        wasteCategoryName.text = result.categoryName
        wastesSum.text = result.wastesSum.currency()

        val adapter = ExpenseAdapter(result.expensesList, this)
        wastesListRecycler.adapter = adapter
        wastesListRecycler.layoutManager = LinearLayoutManager(itemView.context)

        constraintLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        deleteEditing.visibility = View.GONE
        deleteEditing.setOnClickListener {
            DeleteDialogFragment(titleId = R.string.delete_expense, this, itemView.context)
        }
        addWaste.setOnClickListener {
            if (editingExpenseId == 0) {
                val expenseAmount = newWasteAmount.text.toString()
                if (newWasteName.text.isNotEmpty())
                    onAddExpenseListener.addExpense(
                        categoryName = result.categoryName,
                        expenseAmount = if (expenseAmount.isEmpty()) 0f else expenseAmount.toFloat(),
                        expenseName = newWasteName.text.toString()
                    )
            } else {
                if (newWasteName.text.isNotEmpty())
                    onChangeExpenseListener.editExpense(
                        editingExpenseId,
                        newWasteName.text.toString(),
                        newWasteAmount.text.toString().toFloat()
                    )
                it.visibility = View.INVISIBLE
                it.isClickable = false
                editingExpenseId = 0
            }
            newWasteName.text = ""
            newWasteAmount.text = ""
        }

        cardView.setOnClickListener {
            expand()
        }

    }

    fun expand() {
        val v: Int = if (wastesListRecycler.visibility == View.GONE &&
            addWaste.visibility == View.GONE &&
            newWasteName.visibility == View.GONE &&
            newWasteAmount.visibility == View.GONE
        ) {
            View.VISIBLE
        } else {
            View.GONE
        }

        TransitionManager.beginDelayedTransition(constraintLayout, AutoTransition())
        addWaste.visibility = v
        newWasteName.visibility = v
        newWasteAmount.visibility = v
        wastesListRecycler.visibility = v
        if (v == View.GONE)
            deleteEditing.visibility = v
    }

    override fun onChangeDataRequest(expenseId: Int, expenseName: String, expenseAmount: Float) {
        newWasteName.text = expenseName
        newWasteAmount.text = expenseAmount.toInt().toString()
        editingExpenseId = expenseId
        deleteEditing.visibility = View.VISIBLE
        deleteEditing.isClickable = true
    }

    override fun onNegativeButtonClicked() {}

    override fun onPositiveButtonClicked() {
        deleteEditing.visibility = View.GONE
        deleteEditing.isClickable = false
        onChangeExpenseListener.deleteExpense(editingExpenseId)
        editingExpenseId = 0
        newWasteName.text = ""
        newWasteAmount.text = ""
    }
}

/**
 * Адаптер для отображения категорий трат.
 * @param data данные для отображения списка категорий.
 * @param onAddExpenseListener интерфейс для добавления траты.
 */
class ExpenseCategoryAdapter(
    private var data: List<CategoryWithExpenseAndIcon>,
    private var onAddExpenseListener: OnAddExpenseListener,
    private var onChangeExpenseListener: OnChangeExpenseListener
) :
    RecyclerView.Adapter<ExpenseCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseCategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.wastes_recycle_item, parent, false)
        return ExpenseCategoryViewHolder(itemView, onChangeExpenseListener)
    }

    override fun onBindViewHolder(holder: ExpenseCategoryViewHolder, position: Int) {
        holder.bind(result = data[position], onAddExpenseListener = onAddExpenseListener)
        holder.expand()
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<CategoryWithExpenseAndIcon>) {
        data = newData
        notifyDataSetChanged()
    }
}