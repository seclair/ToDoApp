package com.simpleofflineapp.todoapp

import android.app.Application
import com.simpleofflineapp.todoapp.data.ToDoDatabase

class ToDoApplication : Application() {
    val database: ToDoDatabase by lazy { ToDoDatabase.getDatabase(this) }
}