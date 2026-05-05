package com.example.androidplanner.domain

//здесь определяем, что делает наше приложение (т.е выделяем бизнес логику)
interface  PlannerRepository{
    //метод для получения списка заметок
    suspend fun getRecordsList() : List<RecordItem>
    //метод для полкчения данных
    suspend fun getDataRecord(id : Int) : RecordItem?
    //метод для добавления записи
    suspend fun addRecord(record : RecordItem)
    //метод для изменения записи
    fun updateRecord(record: RecordItem)
    //метод для удаления записи
    suspend fun deleteRecord(record: RecordItem)
    //метод для удаления записи по id
    suspend fun deleteRecordById(recordId : Int)
}