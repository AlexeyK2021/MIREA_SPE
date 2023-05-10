package ru.eco.automan.listeners

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Интерфейс, отвечающий за взаимодействие с главами правил ПДД
 */
interface OnChapterClickListener {
    /**
     * Метод, отвечающий за выбор главы правил ПДД
     * @param chapterId ID-номер главы
     */
    fun onChapterClick(chapterId: Int)
}