package com.simpleofflineapp.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//@Database(entities = [ToDoElement::class], version = 1, exportSchema = false)
@Database(entities = [ToDoElement::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoElementDao(): ToDoElementDAO

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null
        fun getDatabase(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {
                // for Database Migration
                val migration = object : Migration(1,2){
                    override fun migrate(database: SupportSQLiteDatabase) {
                        TODO("Not yet implemented")
                    }
                }
                // build Database
                val instance = Room.databaseBuilder(
                    context,
                    ToDoDatabase::class.java,
                    "todo_database"
                )
                    .addMigrations(migration)
                    .build()

                INSTANCE = instance
                instance
                /*
                Room.databaseBuilder(
                    context,
                    ToDoDatabase::class.java,
                    "todo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }*/
            }
        }
    }
}

