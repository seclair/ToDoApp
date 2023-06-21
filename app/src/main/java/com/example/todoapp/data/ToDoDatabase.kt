package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [ToDoElement::class], version = 1, exportSchema = false)
@Database(entities = [ToDoElement::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoElementDao(): ToDoElementDAO
}

