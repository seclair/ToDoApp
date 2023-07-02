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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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
    var expanded by remember { mutableStateOf(false) }
    var selectedMenuItem by remember { mutableStateOf(options[0]) }
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
            OutlinedTextField(
                value = textTitle,
                onValueChange = { textTitle= it },
                modifier = Modifier.fillMaxWidth().onFocusChanged{skipPartiallyExpanded = it.isFocused},
                label = {Text("Your ToDo:")},
                minLines = 3,
                maxLines = 3,
            )
            //Text(element.value.description)
            Spacer(Modifier.height(4.dp))
            Row(modifier = Modifier.align(Alignment.End)){
                ElevatedButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.List, contentDescription = "List")
                    Text(selectedMenuItem.substring(0,7))
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.wrapContentSize()
                ) {
                    options.forEach{ option ->
                        DropdownMenuItem(
                            text = { Text(text = option)},
                            onClick = {
                                selectedMenuItem = option
                                expanded = false
                            })
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButton(
                    onClick = {
                        toDoStatusN=toDoStatusN+1
                    }
                ) {
                    Text("Status: " + toDoStatusN)
                }
            }
            Spacer(Modifier.height(10.dp))
            Row(modifier = Modifier.align(Alignment.End)){
                ElevatedButton(
                    onClick = {
                        showToDoSheet.value = false
                    }
                ) {
                    Text("Discard")
                }
                Spacer(modifier = Modifier.weight(1f))
                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            val updatedToDoElement = ToDoElement(
                                selectedMenuItem,
                                toDoStatusN,
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