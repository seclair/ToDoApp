package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.ToDoDatabase

class ToDoApplication : Application() {
    //val db = Room.databaseBuilder(applicationContext, ToDoDatabase::class.java, "tododatabase02").build()
    //val database: ToDoDatabase by lazy { ToDoDatabase.getDatabase(this) }
    lateinit var database: ToDoDatabase
    override fun onCreate() {
        super.onCreate()
        database = ToDoDatabase.getDatabase(this)
    }
}