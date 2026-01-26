package com.example.planner.model

import android.annotation.SuppressLint
import com.example.planner.help.TypeRecord
import java.time.LocalDate

abstract  class Record {
    private var num : Int = 0;
    private var textRecord : String = "";
    private var titleRecord : String = "";
    @SuppressLint("NewApi")
    private var dateCreateRecord : LocalDate = LocalDate.now();
    private var typeRecord : TypeRecord = TypeRecord.No;
    private var tags : ArrayList<String> = ArrayList<String>();

    fun reateRecord(text: String, dateCreate: LocalDate, type: TypeRecord, num_: Int) {
        if (num_ != -1) {
            num = num_
        }
        textRecord = text
        titleRecord = ""
        dateCreateRecord = dateCreate
        typeRecord = type
        tags = ArrayList<String>();
    }

    fun getTags():ArrayList<String>{
        return tags;
    }

    fun setTag(tag : String){
        if(!tag.isEmpty()){
            if(!tags.contains(tag)){
                tags.add(tag);
            }
        }
    }
}