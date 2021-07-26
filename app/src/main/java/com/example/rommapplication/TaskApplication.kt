package com.example.rommapplication

import android.app.Application
import com.example.rommapplication.data.TaskRepository
import com.example.rommapplication.data.TaskRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TaskApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {TaskRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {TaskRepository(database.taskDao())}

}