package com.example.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.ToDoElement
import kotlinx.coroutines.launch

@Composable
fun ToDoSheet(
    viewModel: ToDoViewModel,
    element: MutableState<ToDoElement>,
    showToDoSheet: MutableState<Boolean>,
    options : List<String>,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
){
    var expandedList by remember { mutableStateOf(false) }
    var selectedMenuItemList by remember { mutableStateOf(options[0]) }
    var expandedStatus by remember { mutableStateOf(false) }
    var selectedMenuItemStatus by remember { mutableStateOf(element.value.status) }
    val coroutineScope = rememberCoroutineScope()
    var textTitle by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(element.value.title))
    }
    var toDoStatusN by remember { mutableStateOf(element.value.status)}
    var skipPartiallyExpanded by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )


    ModalBottomSheet(
        onDismissRequest = { showToDoSheet.value = false },
        sheetState = bottomSheetState,
        modifier = Modifier
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            // Titel des ToDoElements
            OutlinedTextField(
                value = textTitle,
                onValueChange = { textTitle= it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { skipPartiallyExpanded = it.isFocused },
                label = {Text("Your ToDo:")},
                minLines = 3,
                maxLines = 3,
            )
            //Text(element.value.description)
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier.align(Alignment.End)){
                // Drop Down Menu to choose the List
                ElevatedButton(onClick = { expandedList = true }) {
                    Icon(Icons.Default.List, contentDescription = "List")
                    Text(selectedMenuItemList.substring(0,7))
                }
                DropdownMenu(
                    expanded = expandedList,
                    onDismissRequest = { expandedList = false },
                    modifier = Modifier.wrapContentSize()
                ) {
                    options.forEach{ option ->
                        DropdownMenuItem(
                            text = { Text(text = option)},
                            onClick = {
                                selectedMenuItemList = option
                                expandedList = false
                            })
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                // Drop Down Menu to choose the Status
                ElevatedButton(onClick = { expandedStatus = true}) {
                    Icon(Icons.Default.Star, contentDescription = "List")
                    Text(stringResource(id = R.string.status_0+selectedMenuItemStatus))
                }
                DropdownMenu(
                    expanded = expandedStatus,
                    onDismissRequest = { expandedStatus = false },
                    modifier = Modifier.wrapContentSize()
                ) {
                    for(i in 0..3){
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.status_0+i))},
                            onClick = {
                                selectedMenuItemStatus = i
                                expandedStatus = false
                            })
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.align(Alignment.End)){
                // Button to Close without saving
                ElevatedButton(
                    onClick = {
                        showToDoSheet.value = false
                    }
                ) {
                    Text("Discard")
                }
                Spacer(modifier = Modifier.weight(1f))
                // Button to Save the Changes
                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            val updatedToDoElement = ToDoElement(
                                selectedMenuItemList,
                                selectedMenuItemStatus,
                                textTitle.text,
                                ""
                            )
                            updatedToDoElement.id = element.value.id
                            viewModel.updateToDoElement(updatedToDoElement)
                        }
                        showToDoSheet.value = false
                    }
                ) {
                    Text("Save Changes")
                }
            }
            Spacer(modifier = Modifier.weight(10f))
        }
    }
}