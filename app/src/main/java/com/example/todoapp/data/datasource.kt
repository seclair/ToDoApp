package com.example.todoapp.data

import android.content.ClipData
import com.example.todoapp.data.ToDoElement


class Datasource {
    fun loadItems(): List<ToDoElement> {
        return listOf<ToDoElement>(
            ToDoElement("Dies ist das erste ToDo."),
            ToDoElement("Dies ist das zweite ToDo."),
            ToDoElement("Dies ist das dritte ToDo."),
            ToDoElement("Dies ist das vierte ToDo."),
            ToDoElement("Dies ist das erste ToDo, welches gaaanz lang ist."),
            ToDoElement("Dies ist das zweite ToDo."),
            ToDoElement("Dies ist das dritte ToDo."),
            ToDoElement("Dies ist das vierte ToDo."),
            ToDoElement("Dies ist das erste ToDo."),
            ToDoElement("Dies ist das zweite ToDo."),
            ToDoElement("Dies ist das dritte ToDo."),
            ToDoElement("Dies ist das vierte ToDo."),
            ToDoElement("Dies ist das erste ToDo."),
            ToDoElement("Dies ist das zweite ToDo."),
            ToDoElement("Dies ist das dritte ToDo."),
            ToDoElement("Dies ist das vierte ToDo."),
            ToDoElement("Dies ist das erste ToDo."),
            ToDoElement("Dies ist das zweite ToDo."),
            ToDoElement("Dies ist das dritte ToDo."),
            ToDoElement("Dies ist das vierte ToDo."),
            ToDoElement("Dies ist das erste ToDo."),
            ToDoElement("Dies ist das zweite ToDo."),
            ToDoElement("Dies ist das dritte ToDo."),
            ToDoElement("Dies ist das vierte ToDo."),
            ToDoElement("Dies ist das fünfte ToDo.")
        )
    }
}