package com.example.androidplanner.domain

//здесь определяем, что делает наше приложение (т.е выделяем бизнес логику)
interface  PlannerRepository{
    //метод для получения списка заметок
    fun getRecordsList() : List<RecordItem>
    //метод для полкчения данных
    fun getDataRecord(id : Int) : RecordItem?
    //метод для добавления записи
    fun addRecord(record : RecordItem)
    //метод для изменения записи
    fun updateRecord(record: RecordItem)
    //метод для удаления записи
    fun deleteRecord(id : Int)
}