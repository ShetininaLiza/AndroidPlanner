package com.example.planner.model

import java.time.LocalDate

import com.example.planner.help.TypeRecord;

interface IRecord {
    //метод для создания записи
    fun createRecord(text: String?, dateCreate: LocalDate?, type: TypeRecord?, num_: Int)

}