package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow

interface ToDoElementRepository{

    fun getAllToDoElements(): Flow<List<ToDoElement>>

    suspend fun insertToDoElement(toDoElement: ToDoElement)

    suspend fun deleteToDoElement(toDoElement: ToDoElement)

    suspend fun updateToDoElement(toDoElement: ToDoElement)
}