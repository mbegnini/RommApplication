package com.example.rommapplication.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel(private val taskRepository: TaskRepository): ViewModel() {

    val allTasks: LiveData<List<Task>> = taskRepository.allTask.asLiveData()

    fun insert(task: Task) = viewModelScope.launch {
        taskRepository.insert(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        taskRepository.update(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        taskRepository.delete(task)
    }

    fun deleteAll() = viewModelScope.launch {
        taskRepository.deleteAll()
    }

    class TaskViewModelFactory(private val taskRepository: TaskRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return TaskViewModel(taskRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}