package com.example.todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface todoelementDAO {

    @Query("SELECT * from todoelement ORDER BY id ASC")
    fun getAllToDos(): Flow<List<ToDoElement>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoelement: ToDoElement)

    @Delete
    suspend fun delete(toDoElement: ToDoElement)

}