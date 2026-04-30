package com.example.androidplanner.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.androidplanner.presentation.mapper.RecordMapper
import com.example.androidplanner.presentation.models.PresentationRecordItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.*
import com.example.androidplanner.data.repository.DataPlannerRepository
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import kotlinx.coroutines.launch

class RecordViewModel(private val repository: DataPlannerRepository,
    private val mapper : RecordMapper)
    : ViewModel() {
    private val mutableListRecords = MutableStateFlow<List<PresentationRecordItem>>(emptyList())
    val records : StateFlow<List<PresentationRecordItem>> = mutableListRecords

    init {
        loadRecordsList()
    }

    private fun loadRecordsList(){
        viewModelScope.launch {
            mutableListRecords.value = repository.getRecordsList().map { mapper.map(it) }
                //getRecordList().map { mapper.map(it) }
        }
    }
}