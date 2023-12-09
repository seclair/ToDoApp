package com.simpleofflineapp.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoElementDAO {

    // Get the entire Database, sorted by title.
    @Query("SELECT * from DefaultToDoList ORDER BY title ASC")
    fun getAllToDos(): Flow<List<ToDoElement>>

    // Get all ToDos which contain a given Tag.
    @Query("SELECT * from DefaultToDoList WHERE tags = :tag ORDER BY title ASC ")
    fun getTaged(tag: String): Flow<List<ToDoElement>>

    // Gets the ToDos for a given Title.
    @Query("SELECT * from DefaultToDoList WHERE title = :title ORDER BY title ASC")
    fun getTiteled(title: String): Flow<List<ToDoElement>>

    // Get a specific ToDoo by its ID
    @Query("SELECT * from DefaultToDoList WHERE id = :id")
    fun getToDo(id: Int): Flow<ToDoElement>
    
    //-----------------------------------------------------------------------------

    // Add a ToDoElement to the Database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoElement: ToDoElement)
    // Change Data in a given ToDoElement
    @Update
    suspend fun update(toDoElement: ToDoElement)
    @Delete
    suspend fun delete(toDoElement: ToDoElement)

    //@Query("SELECT * FROM ToDoElement ORDER BY id ASC LIMIT 1;\n")
    //fun getFirstToDo(): ToDoElement
}