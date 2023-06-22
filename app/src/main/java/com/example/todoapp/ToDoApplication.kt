package com.example.todoapp

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.ToDoDatabase

class ToDoApplication : Application() {
    val db = Room.databaseBuilder(applicationContext, ToDoDatabase::class.java, "tododatabase02").build()
    //val db: ToDoDatabase by lazy { ToDoDatabase.getDatabase(this) }
}