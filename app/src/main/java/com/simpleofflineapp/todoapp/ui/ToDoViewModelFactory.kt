package com.simpleofflineapp.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simpleofflineapp.todoapp.data.ToDoElementDAO

class ToDoViewModelFactory(private val toDoElementDao: ToDoElementDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoViewModel(toDoElementDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
