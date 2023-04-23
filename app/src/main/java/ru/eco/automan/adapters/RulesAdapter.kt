package ru.eco.automan.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.models.Chapter

class RulesViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView)/*, View.OnClickListener */{
    val chapterName: TextView = itemView.findViewById(R.id.chapter_name)
}

class RulesAdapter(
    private val chapterList: List<Chapter>,
    private val onChapterClickListener: OnChapterClickListener
) :
    RecyclerView.Adapter<RulesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rules_recycle_item, parent, false)
        return RulesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        holder.chapterName.text = chapterList[position].name
        holder.chapterName.setOnClickListener {
            onChapterClickListener.onChapterClick(chapterList[position].id)
        }
    }

    override fun getItemCount(): Int = chapterList.size
}