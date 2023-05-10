package ru.eco.automan.listeners

/**
 * Интерфейс, отвечающий за взаимодействие со списком автомобилей
 */
interface OnAutoChooseClickListener {
    /**
     * Метод удаления автомобиля
     * @param autoId ID-номер автомобиля для удаления
     */
    fun onDeleteClick(autoId: Int)

    /**
     * Метод, отвечающий за обработку выбора автомобиля
     * @param autoId ID-номер автомобиля после выбора
     */
    fun onChooseClick(autoId: Int)
}