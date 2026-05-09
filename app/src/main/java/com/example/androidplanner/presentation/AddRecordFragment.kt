package com.example.androidplanner.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
//import android.widget.Button
import androidx.compose.material3.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import com.example.androidplanner.R
import androidx.lifecycle.*
import androidx.room.Room
import com.example.androidplanner.presentation.models.PresentationRecordItem
import com.example.androidplanner.presentation.viewModels.RecordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddRecordFragment : ComponentActivity(){
    //Fragment(R.layout.fragment_add_record) {
    lateinit var viewModel: RecordViewModel
    /*
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
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateView()
        }
    }

    @Preview
    @Composable
    fun CreateView(){
        Box(modifier = Modifier.fillMaxSize()){
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(value = "Заголовок",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                        .padding(5.dp)
                )
                TextField(value = "Текст",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                        .padding(5.dp)
                )
                Button(onClick = {},
                    modifier = Modifier.fillMaxWidth()
                        .padding(5.dp)
                ){
                    Text("Добавить")
                }
            }
        }
    }
}