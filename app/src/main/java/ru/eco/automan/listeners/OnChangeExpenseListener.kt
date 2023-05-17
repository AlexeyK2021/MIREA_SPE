package ru.eco.automan.listeners

/**
 * Интерфейс, отвечающий за изменение данных о трате
 */
interface OnChangeExpenseListener {
    /**
     * Метод изменения внесенной траты
     * @param expenseId ID-номер траты
     * @param newExpenseName новое имя траты
     * @param newExpenseAmount новая сумма траты
     */
    fun editExpense(expenseId: Int, newExpenseName: String, newExpenseAmount: Float)

    /**
     * Метод удаления внесенной траты
     * @param expenseId ID-номер траты для удаления
     */
    fun deleteExpense(expenseId: Int)
}