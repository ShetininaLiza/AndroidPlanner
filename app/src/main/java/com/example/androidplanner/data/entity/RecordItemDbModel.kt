package com.example.androidplanner.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

//моделька для БД
@Entity(tableName = "records")
class RecordItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    var title : String,
    var text : String,
    val dateCreate : Date
)