package com.example.androidplanner.presentation.mapper

import com.example.androidplanner.domain.RecordItem
import com.example.androidplanner.presentation.models.PresentationRecordItem

//переводим из слоя бизнес-логики в слой представления
class RecordMapper {
    fun map (item : RecordItem) : PresentationRecordItem{
        return PresentationRecordItem(item.id, item.title, item.text)
    }
}