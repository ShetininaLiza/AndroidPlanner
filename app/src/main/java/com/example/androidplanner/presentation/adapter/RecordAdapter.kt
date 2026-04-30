package com.example.androidplanner.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androidplanner.R
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplanner.databinding.RecordItemBinding
import com.example.androidplanner.presentation.models.PresentationRecordItem

//класс для отображения элемента в общем списке
//private val context: Context,
//(private val recordItemList:MutableList<PresentationRecordItem>)
class RecordAdapter(private val context: Context) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {
    var data : List<PresentationRecordItem> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        Log.v("Adapter onCreateViewHolder", "____________onCreateViewHolder")
        val itemView = LayoutInflater.from(context)
            //parent.context)
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
    /*
    //v2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding = RecordItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val foodItem = recordItemList[position]
        holder.bind(foodItem)
    }

    override fun getItemCount(): Int {
        return recordItemList.size
    }
    class RecordViewHolder(recordItemBinding: RecordItemBinding)
        : RecyclerView.ViewHolder(recordItemBinding.root){

        private val binding = recordItemBinding

        fun bind(recordItem: PresentationRecordItem){
            binding.titleText.text = recordItem.title
            //binding.foodItemPriceTV.text = "Rs. ${foodItem.price}"
        }
    }
    */
}