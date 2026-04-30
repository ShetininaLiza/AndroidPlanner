package com.example.androidplanner.data.datastore

import com.example.androidplanner.data.entity.RecordItemDbModel

interface DatabaseDatastore {
    //метод для получения списка заметок
    fun getRecordsList() : List<RecordItemDbModel>
    //метод для полкчения данных
    fun getDataRecord(id : Int) : RecordItemDbModel?
    //метод для добавления записи
    fun addRecord(record : RecordItemDbModel)
    //метод для изменения записи
    fun updateRecord(record: RecordItemDbModel)
    //метод для удаления записи
    fun deleteRecord(id : Int)
}