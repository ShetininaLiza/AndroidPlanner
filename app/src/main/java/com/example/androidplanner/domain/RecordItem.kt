package com.example.androidplanner.domain

import java.util.Date
//моделька в бизнкс слое
data class RecordItem(
    val id : Int,
    val title : String,
    val text : String,
    //val dataCreate : Date
)