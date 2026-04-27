package com.example.androidplanner.domain.useCase

import com.example.androidplanner.domain.PlannerRepository
import com.example.androidplanner.domain.RecordItem

class GetDataRecordUseCase(private val repository: PlannerRepository) {
    operator fun invoke(id : Int) : RecordItem?{
        return repository.getDataRecord(id)
    }
}