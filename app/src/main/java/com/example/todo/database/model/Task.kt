package com.example.todo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true)
     val id :Int,
    @ColumnInfo
     var title:String?=null,
    @ColumnInfo
    var description :String?=null,
    @ColumnInfo
    var data :Long?=null,
    @ColumnInfo
    var isdane:Boolean=false
 )
 {


}