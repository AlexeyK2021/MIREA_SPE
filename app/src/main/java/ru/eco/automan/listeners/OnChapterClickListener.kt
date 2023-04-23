package ru.eco.automan.listeners

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView


interface OnChapterClickListener {
    fun onChapterClick(chapterId: Int)
}