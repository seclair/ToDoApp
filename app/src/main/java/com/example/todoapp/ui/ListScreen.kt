package com.example.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.Datasource
import com.example.todoapp.data.ToDoElement

@Composable
fun ListScreen() {
    ToDoList(todolist = Datasource().loadItems())
}

@Composable
fun ToDoList(todolist: List<ToDoElement>, modifier: Modifier = Modifier){
    LazyColumn{
        items(todolist){todoelement->
            ToDoCard(todoelement)
        }
    }
}

@Composable
fun ToDoCard(todoelement: ToDoElement, modifier: Modifier = Modifier){
    Card(modifier = Modifier.padding(8.dp)){
        Column{
            Text(text = todoelement.description)
        }
    }
}

@Preview
@Composable
private fun ToDoCardPreview(){
    //ToDoCard(ToDoElement("This is a test ToDo."))
}