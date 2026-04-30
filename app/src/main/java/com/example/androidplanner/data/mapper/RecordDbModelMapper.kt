package com.example.androidplanner.data.mapper

import com.example.androidplanner.data.entity.RecordItemDbModel
import com.example.androidplanner.domain.RecordItem

class RecordDbModelMapper {
    fun map(dbModel: RecordItemDbModel) : RecordItem{
        return RecordItem(dbModel.id,
            dbModel.title,
            dbModel.text,
            dbModel.dateCreate)
    }
}