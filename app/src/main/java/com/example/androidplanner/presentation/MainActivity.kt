package com.example.androidplanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.*
import com.example.androidplanner.R
import com.example.androidplanner.data.datastore.AppDatabase
import com.example.androidplanner.data.mapper.RecordDbModelMapper
import com.example.androidplanner.data.repository.DataPlannerRepository
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import com.example.androidplanner.presentation.mapper.RecordMapper
import com.example.androidplanner.presentation.viewModels.RecordViewModel
import com.example.androidplanner.presentation.viewModels.RecordViewModelFacory
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.androidplanner.presentation.models.PresentationRecordItem

class MainActivity : ComponentActivity() {
    lateinit var repository : DataPlannerRepository
    lateinit var getRecordList : GetRecordListUseCase
    private val mapper = RecordMapper()
    lateinit var viewModel : RecordViewModel
    var recordsList : MutableList<PresentationRecordItem> = mutableListOf()

    //первая версия
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "records.db")
            .build()

        repository = DataPlannerRepository(db, RecordDbModelMapper())
        //getRecordList = GetRecordListUseCase(repository)
        viewModel = ViewModelProvider(
            this,
            RecordViewModelFacory(repository, mapper),
        )[RecordViewModel::class.java]
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,RecordsFragment())
                .commit()
        }
    }
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /*
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "records.db")
            .build()
        repository = DataPlannerRepository(db, RecordDbModelMapper())
        viewModel = ViewModelProvider(
            this,
            RecordViewModelFacory(repository, mapper),
        )[RecordViewModel::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observerData()
        */
        setContent {
            CreateView()
        }
    }

    @Composable()
    fun CreateView(){
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
        ) {
            for (item in recordsList){
                Column() {
                    Text(item.title, textAlign = TextAlign.Center)
                    Text(item.text)
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
            modifier =  Modifier.fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Button(
                onClick = {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainer,RecordsFragment())
//                    .commit()
                    Toast.makeText(applicationContext, "ADD_RECORD", Toast.LENGTH_LONG)
                        .show()
                }
            ) {
                Text("+")
            }
        }

    }
    @SuppressLint("RestrictedApi")
    private fun observerData(){
        recordsList.clear()
        //lifecycleScope.launch {}
        viewModel.loadRecordsList()
        val list = viewModel.records.value
        list.forEach {item-> recordsList.add(item)}
        Log.v("MAIN_ACTIVITY_RECORDS", "SIZE____: ${recordsList.size}")
    }
}