package com.example.roomcrud.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "notesTable")
class Notes (@ColumnInfo(name = "title") var noteTitle:String,
             @ColumnInfo(name = "description") var noteDescription:String,
             @ColumnInfo(name = "timestamp") var timestamp:String){

    @PrimaryKey(autoGenerate = true) var id=0
}