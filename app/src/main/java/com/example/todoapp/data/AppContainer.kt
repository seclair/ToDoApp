package com.example.todoapp.data

import android.content.Context

interface AppContainer {
    val toDoElementRepository: ToDoElementRepository
}

class AppDataContainer (private val context: Context) : AppContainer {
    override val toDoElementRepository: ToDoElementRepository by lazy {
        OfflineToDoElementRepository(ToDoDatabase.getDatabase(context).toDoElementDao())
    }
}