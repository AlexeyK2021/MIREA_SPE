package ru.eco.automan.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eco.automan.R
import ru.eco.automan.models.Paragraph

class ParagraphViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val paragraphText: TextView = itemView.findViewById(R.id.paragraph_text)
}

class ParagraphsAdapter(private val paragraphsList: List<Paragraph>) :
    RecyclerView.Adapter<ParagraphViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParagraphViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.paragraph_recycle_item, parent, false)
        return ParagraphViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ParagraphViewHolder, position: Int) {
        holder.paragraphText.text = paragraphsList[position].text
    }

    override fun getItemCount(): Int = paragraphsList.size
}


