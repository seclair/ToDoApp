package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(entities = [ToDoElement::class], version = 1, exportSchema = false)
@Database(entities = [ToDoElement::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoElementDao(): ToDoElementDAO
/*
    // added to try
    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    ToDoDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }*/
}

