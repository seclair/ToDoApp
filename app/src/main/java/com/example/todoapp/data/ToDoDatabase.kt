package com.example.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [ToDoElement::class], version = 1, exportSchema = false)
@Database(entities = [ToDoElement::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoElementDao(): ToDoElementDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase {
            // trying to create a database if non exists jet
            /*if (INSTANCE == null) {
                synchronized(ToDoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "todo_database.db"
                    ).build()
                }
            }*/
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ToDoDatabase::class.java,
                    "todo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}

