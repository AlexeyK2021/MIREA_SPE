package ru.eco.automan.models

import android.graphics.drawable.Drawable
import android.media.Image

data class CategoryWithExpenseAndIcon(
    var categoryName: String,
    var expensesList: List<Expense> = mutableListOf(),
    var wastesSum: Float,
    var categoryImage: Drawable
)