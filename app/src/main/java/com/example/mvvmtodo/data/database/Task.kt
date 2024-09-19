package com.example.mvvmtodo.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String,
    @ColumnInfo var isDone: Boolean,
)