package com.example.todoapp.data


class Datasource {
    fun loadItems(): List<ToDoElement> {
        return listOf<ToDoElement>(
            ToDoElement(1, "Dies ist das erste ToDo.", 0),
            ToDoElement(2, "Dies ist das erste ToDo.", 0),
            ToDoElement(3, "Dies ist das erste ToDo.", 0),
            ToDoElement(4, "Dies ist das erste ToDo.", 0),
        )
    }
}