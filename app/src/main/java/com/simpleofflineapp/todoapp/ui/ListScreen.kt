package com.simpleofflineapp.todoapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simpleofflineapp.todoapp.data.ToDoElement

@Composable
fun ListScreen(
    toDoLists: List<String>,
    chosenToDoList: MutableState<String>,
    onNavigate: (String) -> Unit = {},
    modifier: Modifier
) {
    LazyColumn{
        items(toDoLists){toDoList ->
            ElevatedCard(
                modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 140.dp)
                    .wrapContentSize(Alignment.Center)
                    .padding(4.dp)
                    .clickable {
                        chosenToDoList.value = toDoList
                        onNavigate(ToDoScreen.SingleList.name)
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ){
                Box(modifier = Modifier
                    .fillMaxSize().defaultMinSize(minHeight = 100.dp),
                    contentAlignment = Alignment.Center
                    //.wrapContentSize(Alignment.Center)
                    ){
                    Text(
                        text = toDoList,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewList(){
    val listOfStrings: List<String> = listOf("Apple", "Banana", "Orange", "Grapes")
    var chosenToDoList = remember { mutableStateOf(listOfStrings[0]) }
    ListScreen(toDoLists = listOfStrings, chosenToDoList = chosenToDoList, modifier = Modifier)
}

@Composable
fun SingleListScreen(
    viewModel: ToDoViewModel,
    chosenToDoList: MutableState<String>,
    showToDoSheet: MutableState<Boolean>,
    chosenToDoElement: MutableState<ToDoElement>,
    modifier: Modifier = Modifier
){
    val fullToDoList by viewModel.getToDoList(chosenToDoList.value).collectAsState(emptyList())
    LazyColumn(modifier = modifier, contentPadding = PaddingValues(vertical = 8.dp)) {
        items(
            items = fullToDoList,
            key = { ToDoElement -> ToDoElement.id }
        ) { singletodo ->
            ToDoCard(viewModel, element = singletodo, modifier){
                chosenToDoElement.value = singletodo
                showToDoSheet.value = true
            }
        }
    }
}