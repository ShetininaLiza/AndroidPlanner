package com.example.androidplanner.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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

class RecordsFragment: Fragment(R.layout.fragment_records) {

    var recordsList : MutableList<PresentationRecordItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val recordAdapter =  RecordAdapter(view.context)
        observerData()
        recordAdapter.data= recordsList;
        recordsRecycle.adapter = recordAdapter

        val btnAddRecord = view.findViewById<ImageButton>(R.id.btnAddRecord)

        btnAddRecord.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddRecordFragment())
                .commit()
        }
    }
    @SuppressLint("RestrictedApi")
    private fun observerData(){
        Log.v("observerData", "FRAGMENT || observerData")
        var size = activity?.viewModelStore?.keys()?.size
        Log.v("Fragment", "FRAGMINt || VM: $size")
        activity?.viewModelStore?.keys()?.forEach { k-> Log.v("KEY VIEW MODEL", "KEY VIEW MODEL $k") }
        var viewModel = activity?.let { ViewModelProvider(it).get( RecordViewModel::class.java) }
        Log.v("Fragment", "OK!!!!!!")

        lifecycleScope.launch {
            var list = viewModel?.records?.value
            Log.v("FRAGMENT", list?.size.toString())
            //ЭТО НАДО ДЛЯ ПРОВЕРКИ
            for(i in 1..10 step 1){
                var item = PresentationRecordItem(i,  "Заметка $i", "Заметка $i")
                recordsList.add(item)
            }
        }
    }
}