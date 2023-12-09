package com.simpleofflineapp.todoapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DefaultToDoList")
data class ToDoElement(
    // Title of the ToDoElement, possible short description.
    @NonNull
    @ColumnInfo(name="Title")
    var title: String,
    // Description of the ToDoElement, can be left out.
    @ColumnInfo(name="Description")
    var description: String,
    // Current Status of the ToDoElement.
    @NonNull
    @ColumnInfo(name="Status")
    var status: Int,
    // Tags connected to the ToDoElement. Tags begin with a "#" and are thus separated by #
    @ColumnInfo(name="Tags")
    var tags: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}