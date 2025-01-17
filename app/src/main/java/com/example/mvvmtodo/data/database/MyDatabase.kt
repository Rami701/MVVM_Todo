package com.example.mvvmtodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class MyDatabase:RoomDatabase() {
    abstract fun taskDao(): TaskDao
}