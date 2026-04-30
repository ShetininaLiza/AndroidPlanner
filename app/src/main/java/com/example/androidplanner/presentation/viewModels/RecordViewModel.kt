package com.example.androidplanner.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.androidplanner.presentation.mapper.RecordMapper
import com.example.androidplanner.presentation.models.PresentationRecordItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.*
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import kotlinx.coroutines.launch

class RecordViewModel(private val getRecordList : GetRecordListUseCase,
    private val mapper : RecordMapper)
    : ViewModel() {
    private val mutableListRecords = MutableStateFlow<List<PresentationRecordItem>>(emptyList())
    val records : StateFlow<List<PresentationRecordItem>> = mutableListRecords

    init {
        loadRecordsList()
    }

    private fun loadRecordsList(){
        viewModelScope.launch {
            mutableListRecords.value = getRecordList().map { mapper.map(it) }
        }
        //ЭТО ДЛЯ ПРОВЕРКИ
        /*
        var list: MutableList<PresentationRecordItem> = mutableListOf();
        for(i in 1..5 step 1){
            var data = PresentationRecordItem(0,"Заметка $i", "Текст заметки $i")
            list.add(data)
        }
        mutableListRecords.value = list
        */

    }
}