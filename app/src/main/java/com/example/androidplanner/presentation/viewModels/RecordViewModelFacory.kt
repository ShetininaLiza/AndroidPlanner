package com.example.androidplanner.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidplanner.domain.useCase.GetRecordListUseCase
import com.example.androidplanner.presentation.mapper.RecordMapper

class RecordViewModelFacory(
    private val getRecordListUseCase: GetRecordListUseCase,
    private val mapper: RecordMapper,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            return RecordViewModel(
                getRecordList = getRecordListUseCase,
                mapper = mapper,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}