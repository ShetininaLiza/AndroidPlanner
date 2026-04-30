package com.example.androidplanner.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.androidplanner.R
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.launch

class AddRecordFragment : Fragment(R.layout.fragment_add_record) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var btnSaveRecord = view.findViewById<Button>(R.id.btnSaveRecord)
        btnSaveRecord.setOnClickListener {
            saveRecord(view)
        }
    }

    private fun saveRecord(view: View){
        val title = view.findViewById<TextView>(R.id.text_titleRecord).text
        val text = view.findViewById<TextView>(R.id.text_textRecord).text
        Log.v("AddRecordFragment", "CREATE RECORD || TITLE: $title, TEXT: $text")
        //TODO - надо сохранить в БД
        //lifecycleScope.launch {  }
    }
}