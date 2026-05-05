package com.example.androidplanner.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidplanner.data.datastore.AppDatabase
import com.example.androidplanner.data.datastore.DatabaseDatastore
import com.example.androidplanner.data.datastore.ServerDatastore
import com.example.androidplanner.data.mapper.CatFactDtoMapper
import com.example.androidplanner.data.mapper.RecordDbModelMapper
import com.example.androidplanner.domain.PlannerRepository
import com.example.androidplanner.domain.RecordItem
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import java.sql.Date

class DataPlannerRepository(
    private val database: AppDatabase,
    //private val server : ServerDatastore,
    //private val factDtoMapper: CatFactDtoMapper,
    private val recordDbMapper : RecordDbModelMapper
): PlannerRepository {

    override suspend fun getRecordsList(): List<RecordItem> {
        Log.v("REPOSYTORY", "getRecordsList")
        var list : List<RecordItem> = emptyList()
        coroutineScope {
            //запускаем в общем пуле потоков
            launch(Dispatchers.IO) {
                val buf = database.recordDao().getRecordsList()
                list = buf.map { recordDbMapper.map(it) }
                Log.v("REPOSYTORY", "getRecordsList || ${list.size}")
            }
        }
        return list
    }

    override suspend fun getDataRecord(id: Int): RecordItem? {
        var result :  RecordItem? = null
        /*
        coroutineScope{
            launch {
                val buf = database.recordDao().getDataRecord(id)
                result = buf?.let { recordDbMapper.map(it) }
            }
        }
        */
        GlobalScope.launch {
            val buf = database.recordDao().getDataRecord(id)
            result = buf?.let { recordDbMapper.map(it) }
        }
        return result
    }

    override suspend fun addRecord(record: RecordItem) {
        coroutineScope{
            launch(Dispatchers.IO) {
                val item = recordDbMapper.mapToDbModel(record)
                database.recordDao().addRecord(item)
                Log.v("REPOSYTORY", "addRecord || SAVE")
            }
        }
    }

    override fun updateRecord(record: RecordItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecord(record: RecordItem) {
        coroutineScope{
            launch(Dispatchers.IO) {
                val item = recordDbMapper.mapToDbModel(record)
                database.recordDao().deleteRecord(item)
                Log.v("REPOSYTORY", "deleteRecord || DELETE")
            }
        }
    }
    override suspend fun deleteRecordById(recordId : Int){
        coroutineScope{
            launch(Dispatchers.IO) {
                database.recordDao().deleteRecordById(recordId)
                Log.v("REPOSYTORY", "deleteRecordById || DELETE_BY_ID")
            }
        }
    }
}