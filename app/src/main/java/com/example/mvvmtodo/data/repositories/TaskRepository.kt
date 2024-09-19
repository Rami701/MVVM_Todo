package com.example.mvvmtodo.data.repositories

import androidx.lifecycle.LiveData
import com.example.mvvmtodo.data.database.Task
import com.example.mvvmtodo.data.database.TaskDao
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAll()

    suspend fun insert(task: Task): Long {
        return taskDao.insert(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun update(task: Task){
        taskDao.update(task)
    }
}