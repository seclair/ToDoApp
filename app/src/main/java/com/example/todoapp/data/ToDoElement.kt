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
    var listTitle: String,
    // Current Status of the ToDoElement
    @NonNull
    @ColumnInfo(name="Status")
    var status: Int,
    // Title of the ToDoElement
    @NonNull
    @ColumnInfo(name="Title")
    var title: String,
    // Description of the ToDoElement
    @ColumnInfo(name="Description")
    var description: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}