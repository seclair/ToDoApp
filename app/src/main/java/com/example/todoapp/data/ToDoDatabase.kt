package com.example.todoapp.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [ToDoElement::class], version = 1, exportSchema = false)
@Database(entities = [ToDoElement::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoElementDao(): ToDoElementDAO

    // added to try
    companion object {
        @Volatile
        private var Instance: ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase {
            Log.d("Room", "Going into getDatabase")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext,
                    ToDoDatabase::class.java,"todo_database42")
                    .build().also{Instance = it}
            }
        }
    }
}

