package com.example.todoapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DefaultToDoList")
data class ToDoElement(
    // Name of the ToDoList the Element is a part of.
    @NonNull
    @ColumnInfo(name="List")
    val listTitle: String,
    // Current Status of the ToDoElement
    @NonNull
    @ColumnInfo(name="Status")
    val status: Int,
    // Title of the ToDoElement
    @NonNull
    @ColumnInfo(name="Title")
    val title: String,
    // Description of the ToDoElement
    @ColumnInfo(name="Description")
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}