package ru.eco.automan.listeners

interface OnAddExpenseListener {
    fun addExpense(categoryName: String, expenseName: String, expenseAmount: Float)
}