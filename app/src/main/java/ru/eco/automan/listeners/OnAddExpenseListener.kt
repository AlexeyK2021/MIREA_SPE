package ru.eco.automan.listeners

/**
 * Интерфейс, отвечающий за добавление новой траты
 */
interface OnAddExpenseListener {
    /**
     * Метод добавления новой траты
     * @param categoryName имя категории
     * @param expenseName имя траты
     * @param expenseAmount сумма траты
     */
    fun addExpense(categoryName: String, expenseName: String, expenseAmount: Float)
}