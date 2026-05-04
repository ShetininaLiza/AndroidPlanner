package com.example.androidplanner.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidplanner.R
import androidx.lifecycle.*
import androidx.room.Room
import com.example.androidplanner.presentation.models.PresentationRecordItem
import com.example.androidplanner.presentation.viewModels.RecordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddRecordFragment : Fragment(R.layout.fragment_add_record) {
    lateinit var viewModel: RecordViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = activity?.let { ViewModelProvider(it).get( RecordViewModel::class.java) }!!
        val size = viewModel!!.records.value.size.toString()
        Log.v("ADD", "RECORDS: $size")

        val btnSaveRecord = view.findViewById<Button>(R.id.btnSaveRecord)
        btnSaveRecord.setOnClickListener {
            saveRecord(view)
        }
    }

    private fun saveRecord(view: View){
        val title = view.findViewById<TextView>(R.id.text_titleRecord).text.toString()
        val text = view.findViewById<TextView>(R.id.text_textRecord).text.toString()
        Log.v("AddRecordFragment", "CREATE RECORD || TITLE: $title, TEXT: $text")
        //lifecycleScope.launch {
        runBlocking {
            val record = PresentationRecordItem(0, title, text)
            viewModel.addRecord(record)
            Toast.makeText(view.context,  "Сохранено", Toast.LENGTH_LONG).show()
            delay(1000)
            requireActivity().onBackPressed()
        }
        //requireActivity().onBackPressed()
    }
}