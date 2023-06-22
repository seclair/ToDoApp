package com.example.todoapp.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.R
import com.example.todoapp.ToDoApplication


enum class ToDoScreen (@StringRes val title: Int) {
    Start(title = R.string.screen_start_name),
    List(title = R.string.screen_list_name),
    AddElement(title = R.string.screen_add_name)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp(
    //viewModel: ToDoViewModel = viewModel(factory = ToDoViewModel.factory),
    toDoApplication: ToDoApplication = ToDoApplication(),
    modifier: Modifier = Modifier
) {
    Log.d("Room", "Starting fun ToDoApp")
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ToDoScreen.valueOf( backStackEntry?.destination?.route ?: ToDoScreen.Start.name)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (currentScreen != ToDoScreen.AddElement){
                    navController.navigate(ToDoScreen.AddElement.name)
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                tonalElevation = 1.dp,

                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Button(onClick = {
                            if (currentScreen != ToDoScreen.Start){
                                navController.navigate(ToDoScreen.Start.name){
                                    popUpTo(ToDoScreen.Start.name){ inclusive = true }
                                }
                            }
                        },modifier = Modifier.weight(1f)){
                            Text("Start")
                        }
                        //Spacer(Modifier.weight(1f, true))
                        Button(onClick = {
                            if (currentScreen != ToDoScreen.List){
                                navController.navigate(ToDoScreen.List.name){
                                    popUpTo(ToDoScreen.List.name){ inclusive = true }
                                }
                            }
                        },modifier = Modifier.weight(1f)){
                            Text("List")
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ToDoScreen.Start.name,
            modifier = modifier.padding(innerPadding))
        {
            composable(route = ToDoScreen.Start.name) {
                StartScreen()
            }
            composable(route = ToDoScreen.List.name) {
                ListScreen ()
            }
            composable(route = ToDoScreen.AddElement.name) {
                AddElementScreen()
            }
        }
    }
}

@Preview
@Composable
fun testToDoScreen(){
    ToDoApp()
}