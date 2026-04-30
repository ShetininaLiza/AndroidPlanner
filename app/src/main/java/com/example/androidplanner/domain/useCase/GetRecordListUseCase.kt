package com.example.androidplanner.domain.useCase

import com.example.androidplanner.domain.PlannerRepository
import com.example.androidplanner.domain.RecordItem

class GetRecordListUseCase(private val repository: PlannerRepository) {
    suspend operator fun invoke(): List<RecordItem> {
        return repository.getRecordsList()
    }
}