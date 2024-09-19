package com.example.mvvmtodo.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmtodo.data.database.MyDatabase
import com.example.mvvmtodo.data.database.TaskDao
import com.example.mvvmtodo.data.repositories.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase{
        return Room.databaseBuilder(context, MyDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: MyDatabase): TaskDao {
        return database.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao:TaskDao): TaskRepository  {
        return TaskRepository(taskDao)
    }
}