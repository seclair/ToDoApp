package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoElementDAO {

    // Gets the entire Default ToDos List.
    @Query("SELECT * from DefaultToDoList ORDER BY title ASC")
    fun getAllToDos(): Flow<List<ToDoElement>>
    // Gets the ToDos for a specific Title.
    @Query("SELECT * from DefaultToDoList WHERE title = :title ORDER BY title ASC")
    fun getToDos(title: String): Flow<List<ToDoElement>>
    // Add a ToDoElement to the Database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoElement: ToDoElement)
    //@Update
    //suspend fun update(toDoElement: ToDoElement)
    @Delete
    suspend fun delete(toDoElement: ToDoElement)
    //@Query("SELECT * FROM ToDoElement ORDER BY id ASC LIMIT 1;\n")
    //fun getFirstToDo(): ToDoElement
}