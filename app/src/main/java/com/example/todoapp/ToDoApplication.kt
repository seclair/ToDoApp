package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.ToDoDatabase

class ToDoApplication : Application() {
    val database: ToDoDatabase by lazy { ToDoDatabase.getDatabase(this) }
}