package com.example.androidplanner.data.datastore

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidplanner.data.entity.RecordItemDbModel

@Dao
interface DatabaseDatastore {
    @Query("SELECT * FROM records")
    //метод для получения списка заметок
    fun getRecordsList() : List<RecordItemDbModel>

    @Query("SELECT * FROM records WHERE id = :recordId")
    //метод для полкчения данных
    fun getDataRecord(recordId : Int) : RecordItemDbModel?

    @Insert
    //метод для добавления записи
    fun addRecord(record : RecordItemDbModel)

    @Update
    //метод для изменения записи
    fun updateRecord(record: RecordItemDbModel)

    @Delete
    //метод для удаления записи
    fun deleteRecord(record: RecordItemDbModel)
}