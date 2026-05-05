package com.example.androidplanner.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androidplanner.presentation.mapper.RecordMapper
import com.example.androidplanner.presentation.models.PresentationRecordItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.*
import com.example.androidplanner.data.repository.DataPlannerRepository
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecordViewModel(private val repository: DataPlannerRepository,
    private val mapper : RecordMapper)
    : ViewModel() {
    private val mutableListRecords = MutableStateFlow<List<PresentationRecordItem>>(emptyList())
    val records : StateFlow<List<PresentationRecordItem>> = mutableListRecords

    init {
        loadRecordsList()
    }

    fun loadRecordsList(){
        //блокируем код, пока не считаем из БД данные
        runBlocking {
            mutableListRecords.value = repository.getRecordsList()
                .map { mapper.map(it) }
        }
    }
    fun addRecord(record : PresentationRecordItem){
        viewModelScope.launch {
            val item = mapper.mapToItem(record)
            repository.addRecord(item)
            Log.v("VIEW_MODEL", "VIEW_MODEL || addRecord")
        }
    }

    fun removeRecordById(id : Int){
        viewModelScope.launch {
            repository.deleteRecordById(id)
            Log.v("VIEW_MODEL", "VIEW_MODEL || addRecord")
        }
    }
}