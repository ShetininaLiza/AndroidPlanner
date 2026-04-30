package com.example.androidplanner.data.datastore

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidplanner.data.entity.RecordItemDbModel

@Database(entities = [RecordItemDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun recordDao(): DatabaseDatastore
}