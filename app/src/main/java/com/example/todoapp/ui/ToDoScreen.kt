package com.example.todoapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.ToDoElement

enum class ToDoScreen {
    StartScreen,
    ListScreen
}

@Composable
fun ToDoApp(
    viewModel: ToDoViewModel = viewModel(factory = ToDoViewModel.factory)
) {
    val navController = rememberNavController()
    val startScreenTitle = "StartScreen"
    val topAppBarTitle by remember { mutableStateOf(startScreenTitle) }
    val fullToDoList by viewModel.getAllToDos().collectAsState(emptyList())

    // For Add ToDoElement Dialog
    var showDialog = remember { mutableStateOf(false) }
    val options = listOf("Default List", "Einkaufs Liste", "Lese Liste")
    val focusRequester = remember { FocusRequester() }

    // For ToDoSheen
    var showToDoSheet = remember { mutableStateOf(false) }
    var chosenToDoElement = remember { mutableStateOf(ToDoElement("",0,"","")) }

    Scaffold(
        // Top Bar for Infos
        topBar = {ToDoTopAppBar(title = topAppBarTitle)},
        // Bottom Bar for Navigation
        bottomBar = {ToDoNavigationBar()},
        // Button to Add a ToDoElement
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog.value = true },
                content = { Icon(Icons.Filled.Add, contentDescription = "Add") }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = ToDoScreen.StartScreen.name
        ) {
            composable(ToDoScreen.StartScreen.name) {
                StartScreen(viewModel, toDoElements = fullToDoList, showToDoSheet, chosenToDoElement, Modifier)
            }
        }
    }
    // The ToDoSheet to display and edit ToDoElement information
    if(showToDoSheet.value){
        ToDoSheet(
            viewModel,
            chosenToDoElement,
            showToDoSheet,
            options,
            focusRequester = focusRequester
        )
    }
    // The Add Element Dialog
    if (showDialog.value) {
        AddToDoElementDialog(
            viewModel = viewModel,
            onDismissRequest = { showDialog.value = false },
            options,
            focusRequester = focusRequester
        )
    }
}

// The TopBar to hold Information
@Composable
fun ToDoTopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier
    )
}

// The bottom App Bar to hold the navigation
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToDoNavigationBar(){
    var selectedItem by remember { mutableStateOf(0)}
    var items = listOf("Start", "Lists")
    NavigationBar{
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when(item){
                        "Start" -> Icon(Icons.Filled.Home, contentDescription = item)
                        "Lists" -> Icon(Icons.Filled.List, contentDescription = item)
                    }
                },
                label = {Text(item)},
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }

}
// preview of the top & bottom app bars
@Preview
@Composable
private fun ToDoScreenPreview(){
    ToDoApp()
}