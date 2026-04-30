package com.example.androidplanner.data.repository

import com.example.androidplanner.data.datastore.AppDatabase
import com.example.androidplanner.data.datastore.DatabaseDatastore
import com.example.androidplanner.data.datastore.ServerDatastore
import com.example.androidplanner.data.mapper.CatFactDtoMapper
import com.example.androidplanner.data.mapper.RecordDbModelMapper
import com.example.androidplanner.domain.PlannerRepository
import com.example.androidplanner.domain.RecordItem
import java.sql.Date

class DataPlannerRepository(
    private val database: AppDatabase,
    //private val server : ServerDatastore,
    //private val factDtoMapper: CatFactDtoMapper,
    private val recordDbMapper : RecordDbModelMapper
): PlannerRepository {

    override fun getRecordsList(): List<RecordItem> {
       //val list = database.recordDao().getRecordsList()
       //return list.map { recordDbMapper.map(it) }
        //ЭТО НАДО ДЛЯ ПРОВЕРКИ
        return emptyList()
    }

    override fun getDataRecord(id: Int): RecordItem? {
        //val result = database.recordDao().getDataRecord(id)
        //return result?.let { recordDbMapper.map(it) }
        //ЭТО НАДО ДЛЯ ПРОВЕРКИ
        return RecordItem(id,  "Заметка $id", "Заметка $id")
    }

    override fun addRecord(record: RecordItem) {
        TODO("Not yet implemented")
    }

    override fun updateRecord(record: RecordItem) {
        TODO("Not yet implemented")
    }

    override fun deleteRecord(id: Int) {
        TODO("Not yet implemented")
    }
}