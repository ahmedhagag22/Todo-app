package com.example.todo.database.dao

import androidx.room.*
import com.example.todo.database.model.Task

@Dao
interface TaskDoa {
    @Insert
    fun insert(task: Task)
    @Delete
    fun delete(task: Task)
    @Query("SELECT * FROM tasks ")
    fun getAllTasks():List<Task>
    @Query("SELECT *FROM tasks WHERE date=:date")
    fun getTasksByDate(date:Long):List<Task>
    @Update
    fun update (task: Task)

}