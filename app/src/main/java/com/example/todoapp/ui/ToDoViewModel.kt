package com.example.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todoapp.ToDoApplication
import com.example.todoapp.data.ToDoElementDAO

class ToDoViewModel(private val toDoElementDao: ToDoElementDAO): ViewModel() {
    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ToDoApplication)
                ToDoViewModel(application.db.toDoElementDao())
            }
        }
    }
}