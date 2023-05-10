package ru.eco.automan.models

import android.graphics.drawable.Drawable
import android.media.Image

/**
 * Класс для отображения категории, траты и иконки.
 * @param categoryName имя категории
 * @param expensesList список трат в этой категории
 * @param wastesSum сумма трат
 * @param categoryImage иконка категории
 */
data class CategoryWithExpenseAndIcon(
    var categoryName: String,
    var expensesList: List<Expense> = mutableListOf(),
    var wastesSum: Float,
    var categoryImage: Drawable
)