package com.example.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoElement (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    val status: Int
)