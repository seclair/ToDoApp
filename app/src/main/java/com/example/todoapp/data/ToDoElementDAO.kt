package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoElementDAO {

    @Query("SELECT * from todoelement ORDER BY id ASC")
    fun getAllToDos(): Flow<List<ToDoElement>>

    @Query("SELECT * from todoelement WHERE id = :id")
    fun getToDo(id: Int): Flow<ToDoElement>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoElement: ToDoElement)

    @Update
    suspend fun update(toDoElement: ToDoElement)

    @Delete
    suspend fun delete(toDoElement: ToDoElement)

    //@Query("SELECT * FROM ToDoElement ORDER BY id ASC LIMIT 1;\n")
    //fun getFirstToDo(): ToDoElement

}