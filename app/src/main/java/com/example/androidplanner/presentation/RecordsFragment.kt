package com.example.androidplanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.compose.AndroidFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.compose.ComposableFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplanner.R
import com.example.androidplanner.data.datastore.DatabaseDatastore
import com.example.androidplanner.data.repository.DataPlannerRepository
import com.example.androidplanner.domain.PlannerRepository
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import com.example.androidplanner.presentation.adapter.RecordAdapter
import com.example.androidplanner.presentation.mapper.RecordMapper
import com.example.androidplanner.presentation.models.PresentationRecordItem
import com.example.androidplanner.presentation.viewModels.RecordViewModel
import com.example.androidplanner.presentation.viewModels.RecordViewModelFacory
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import kotlin.toString

class RecordsFragment: Fragment(R.layout.fragment_records)
{
    var recordsList : MutableList<PresentationRecordItem> = mutableListOf()
    lateinit var viewModel: RecordViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.viewModelStore?.keys()?.forEach { k-> Log.v("KEY VIEW MODEL", "KEY VIEW MODEL $k") }
        viewModel = activity?.let { ViewModelProvider(it).get( RecordViewModel::class.java) }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recordsRecycle = view.findViewById<RecyclerView>(R.id.recordsList)
        recordsRecycle.layoutManager = LinearLayoutManager(view.context)
        val recordAdapter =  RecordAdapter(view.context, viewModel)
        observerData()
        recordAdapter.data= recordsList;
        Log.v("RECORDS", "SIZE: ${recordsList.size}")
        recordsRecycle.adapter = recordAdapter

        /*
        val btnAddRecord = view.findViewById<ImageButton>(R.id.btnAddRecord)
        btnAddRecord.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddRecordFragment())
                .addToBackStack(null)
                .commit()
        }
        */
    }
    @SuppressLint("RestrictedApi")
    private fun observerData(){
        recordsList.clear()
        //lifecycleScope.launch {}
        viewModel.loadRecordsList()
        val list = viewModel.records.value
        list.forEach {item-> recordsList.add(item)}
        Log.v("RECORDS", "SIZE____: ${recordsList.size}")
    }

    @Preview(showSystemUi = true)
    @Composable()
    fun CreateView(){
        Text(text = "Records Fragments",
            style = TextStyle(
                fontSize = 28.sp
            )
        )
    }
}