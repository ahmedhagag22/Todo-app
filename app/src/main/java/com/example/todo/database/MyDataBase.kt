package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TaskDoa
import com.example.todo.database.model.Task

@Database(entities = [Task::class], version = 3, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getDao(): TaskDoa




    companion object{
        private var myDataBase: MyDataBase? = null
        private val dataBaseName = "TasksDataBaseTODo"
        fun getDataBase(context: Context): MyDataBase {
            if (myDataBase == null) {
                //initialize
                myDataBase = Room.databaseBuilder(
                    context, MyDataBase::class.java,

                    dataBaseName
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return myDataBase!!
        }

    }
}