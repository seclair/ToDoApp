package com.example.todoapp.data

import kotlinx.coroutines.flow.Flow

class OfflineToDoElementRepository(private val toDoElementDAO: ToDoElementDAO): ToDoElementRepository {
    override fun getAllToDoElements(): Flow<List<ToDoElement>> = toDoElementDAO.getAllToDos()
    override suspend fun insertToDoElement(toDoElement: ToDoElement) = toDoElementDAO.insert(toDoElement)
    override suspend fun updateToDoElement(toDoElement: ToDoElement) = toDoElementDAO.update(toDoElement)
    override suspend fun deleteToDoElement(toDoElement: ToDoElement) = toDoElementDAO.delete(toDoElement)
}