package ru.eco.automan.listeners

/**
 * Интерфейс, отвечающий за изменение данных о трате
 */
interface OnChangeExpenseDataRequestListener {
    /**
     * Метод запроса изменения внесенной траты
     * @param expenseId ID-номер траты
     * @param expenseName имя траты
     * @param expenseAmount сумма траты
     */
    fun onChangeDataRequest(expenseId: Int, expenseName: String, expenseAmount: Float)
}
