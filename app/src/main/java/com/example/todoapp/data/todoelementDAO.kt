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

    @Query("SELECT * from ToDoElement ORDER BY id ASC")
    fun getAllToDos(): Flow<List<ToDoElement>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDoElement: ToDoElement)

    @Update
    suspend fun update(toDoElement: ToDoElement)

    @Delete
    suspend fun delete(toDoElement: ToDoElement)

}