package com.simpleofflineapp.todoapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.simpleofflineapp.todoapp.ToDoApplication
import com.simpleofflineapp.todoapp.data.ToDoElement
import com.simpleofflineapp.todoapp.data.ToDoElementDAO
import kotlinx.coroutines.flow.Flow

class ToDoViewModel(private val toDoElementDao: ToDoElementDAO): ViewModel() {
    // Get all ToDos.
    fun getAllToDos(): Flow<List<ToDoElement>> = toDoElementDao.getAllToDos()
    // Get all ToDos in a given List.
    fun getToDoList(listName: String): Flow<List<ToDoElement>> = toDoElementDao.getTaged(listName) 
    // Add a ToDoElement to the Database
    suspend fun addToDoElement(toDoElement: ToDoElement){
        toDoElementDao.insert(toDoElement)
    }
    // Delete a ToDoElement from the Database
    suspend fun deleteToDoElement(toDoElement: ToDoElement){
        toDoElementDao.delete(toDoElement)
    }
    // Update a ToDoElement from the Database
    suspend fun updateToDoElement(toDoElement: ToDoElement){
        toDoElementDao.update(toDoElement)
    }
    suspend fun updateToDoElementStatus(toDoElement: ToDoElement, newStatus: Int){
        toDoElement.status = newStatus
        toDoElementDao.update(toDoElement)
    }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ToDoApplication)
                ToDoViewModel(application.database.toDoElementDao())
            }
        }
    }
}