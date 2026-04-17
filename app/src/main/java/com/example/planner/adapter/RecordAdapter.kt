package com.example.planner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.planner.R
import androidx.recyclerview.widget.RecyclerView
import com.example.planner.model.RecordItemClass

//класс для отображения элемента в общем списке
class RecordAdapter : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {
    var data : List<RecordItemClass> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_item, parent, false)
        return RecordViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: RecordViewHolder,
        pozition: Int
    ) {
        // let — одна из функций области видимости (scope functions),
        // которая позволяет выполнить блок кода в контексте объекта.
        data[pozition].let {
            holder.textTitle.setText(data[pozition].title)
        }
    }

    override fun getItemCount(): Int = data.size

    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textTitle = itemView.findViewById<TextView>(R.id.titleText)
    }
}