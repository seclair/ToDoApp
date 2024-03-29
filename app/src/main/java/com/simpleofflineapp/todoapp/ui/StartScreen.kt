package com.simpleofflineapp.todoapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.simpleofflineapp.todoapp.data.ToDoElement

// This is the Start Screen which welcomes you and holds all important Information. In the future it should contain the relevant ToDos of all your ToDoLists
@Composable
fun StartScreen(
    viewModel: ToDoViewModel,
    toDoElements: List<ToDoElement>,
    showToDoSheet: MutableState<Boolean>,
    chosenToDoElement: MutableState<ToDoElement>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(vertical = 8.dp)) {
        items(
            items = toDoElements,
            key = { ToDoElement -> ToDoElement.id }
        ) { singletodo ->
            ToDoCard(viewModel, element = singletodo, modifier){
                chosenToDoElement.value = singletodo
                showToDoSheet.value = true
            }
        }
    }
}