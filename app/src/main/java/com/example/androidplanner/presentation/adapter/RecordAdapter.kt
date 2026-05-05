package com.example.androidplanner.presentation.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.androidplanner.R
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplanner.presentation.models.PresentationRecordItem
import com.example.androidplanner.presentation.viewModels.RecordViewModel

//класс для отображения элемента в общем списке
//private val context: Context,
//(private val recordItemList:MutableList<PresentationRecordItem>)
class RecordAdapter(private val context: Context, private val viewModel : RecordViewModel) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {
    var data : List<PresentationRecordItem> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        Log.v("Adapter onCreateViewHolder", "____________onCreateViewHolder")
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.record_item, parent, false)
        val viewHolder = RecordViewHolder(itemView)
        Log.v("ADAPTER", "____________ADAPTER || ID: ${viewHolder.idRecord}")
        //кнопка для удаления заметки
        val btnDell = itemView.findViewById<ImageButton>(R.id.btnDelRecord)
        btnDell.setOnClickListener { delRecord(viewHolder.idRecord) }

        //return RecordViewHolder(itemView)
        return viewHolder
    }
    fun delRecord(id : Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Удаление")
        //id - оставила для проверки
        builder.setMessage("Вы действительно хотите удалить данную заметку ${id}?")
        //крестик
        //builder.setIcon(android.R.drawable.ic_delete)
        //корзинка
        builder.setIcon(android.R.drawable.ic_menu_delete)
        builder.setPositiveButton("Нет", null)
        builder.setNegativeButton("Да"){
                dialog, which ->
            viewModel.removeRecordById(id)
            viewModel.loadRecordsList()
            data = viewModel.records.value
        }
        builder.show()
    }
    override fun onBindViewHolder(
        holder: RecordViewHolder,
        pozition: Int
    ) {
        // let — одна из функций области видимости (scope functions),
        // которая позволяет выполнить блок кода в контексте объекта.
        data[pozition].let {
            holder.idRecord = data[pozition].id
            holder.titleRecord.setText(data[pozition].title)
            holder.textRecord.setText(data[pozition].text)
        }
    }

    override fun getItemCount(): Int = data.size
    class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //id заметки
        var idRecord: Int = 0
        //заголовок заметки
        val titleRecord = itemView.findViewById<TextView>(R.id.text_titleRecord)
        //текст заметки
        val textRecord = itemView.findViewById<TextView>(R.id.textRecord)
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