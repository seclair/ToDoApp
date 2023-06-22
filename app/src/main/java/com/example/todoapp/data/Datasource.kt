package com.example.todoapp.data


class Datasource {
    fun loadItems(): List<ToDoElement> {
        return listOf<ToDoElement>(
            ToDoElement(1, "Dies ist das erste ToDo."),
            ToDoElement(2, "Dies ist das erste ToDo."),
            ToDoElement(3, "Dies ist das erste ToDo."),
            ToDoElement(4, "Dies ist das erste ToDo."),
        )
    }
}