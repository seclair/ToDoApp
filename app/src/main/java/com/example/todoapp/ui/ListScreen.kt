package com.example.todoapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todoapp.data.ToDoElement

@Composable
fun ListScreen() {

}

@Composable
fun ToDoList(todolist: List<ToDoElement>, modifier: Modifier = Modifier){
    LazyColumn{
        items(todolist){todoelement->
            ToDoCard(todoelement)
        }
    }
}