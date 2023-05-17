package ru.eco.automan.listeners

/**
 * Интерфейс, отвечающий за передачу выбора пользователя из всплывающего окна
 */
interface OnDialogListener {
    /**
     * Метод обработки нажатия кнопки Нет
     */
    fun onNegativeButtonClicked()

    /**
     * Метод обработки нажатия кнопки Да
     */
    fun onPositiveButtonClicked()
}