package com.example.planner.help

/*
enum class TypeRecord {
    No,
    Note, //заметка
    Member //напоминаеие
}
*/
sealed class TypeRecord{
    object No : TypeRecord()
    object Note : TypeRecord() //заметка
    object Member: TypeRecord() //напоминаеие
}