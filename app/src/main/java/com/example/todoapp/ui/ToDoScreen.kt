package com.example.todoapp.ui

// added

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



/*enum class ToDoScreen (@StringRes val title: Int) {
    Start(title = R.string.screen_start_name),
    List(title = R.string.screen_list_name),
    AddElement(title = R.string.screen_add_name)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp(
    viewModel: ToDoViewModel = viewModel(factory = ToDoViewModel.factory)
    //toDoApplication: ToDoApplication = ToDoApplication(),
    //modifier: Modifier = Modifier
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
            modifier = Modifier.padding(innerPadding))
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
}*/

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
    var topAppBarTitle by remember { mutableStateOf(startScreenTitle) }
    val fullToDoList by viewModel.getAllToDos().collectAsState(emptyList())
    val onBackHandler = {
        topAppBarTitle = startScreenTitle
        navController.navigateUp()
    }

    Scaffold(
        topBar = {
            ToDoTopAppBar(
                title = topAppBarTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackClick = { onBackHandler() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = ToDoScreen.StartScreen.name
        ) {
            composable(ToDoScreen.StartScreen.name) {
                //FullToDoScreen(
                 //   toDoElements = fullToDoList
                //)
                ToDoScreen(toDoElements = fullToDoList)

            }
        }
    }
}

// The TopBar to hold Information

@Composable
fun ToDoTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            modifier = modifier
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier
        )
    }
}

// The bottom App Bar to hold the navigation
@Composable
fun ToDoBottomAppBar(){

}
// preview of the top & bottom app bars