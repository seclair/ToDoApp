package com.simpleofflineapp.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.simpleofflineapp.todoapp.data.ToDoElement
import com.simpleofflineapp.todoapp.R
import kotlinx.coroutines.launch

// TheAddItemDialog, called from the ToDoApp
@Composable
fun AddToDoElementDialog(
    viewModel: ToDoViewModel,
    onDismissRequest: () -> Unit,
    options : List<String>,
    focusRequester: FocusRequester
){
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 1)))
    }

    var expandedList by remember { mutableStateOf(false) }
    var selectedMenuItem by remember { mutableStateOf(options[0]) }
    var expandedStatus by remember { mutableStateOf(false) }
    var selectedMenuItemStatus by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()


    Dialog(
        onDismissRequest = onDismissRequest
    ){
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .fillMaxWidth().heightIn(),
                    label = {Text("Your ToDo:")},
                    minLines = 3,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.align(Alignment.End)){
                    ElevatedButton(onClick = { expandedList = true }) {
                        Icon(Icons.Default.List, contentDescription = "List")
                        Text(selectedMenuItem.substring(0,7))
                    }
                    DropdownMenu(
                        expanded = expandedList,
                        onDismissRequest = { expandedList = false },
                        modifier = Modifier.wrapContentSize(),
                        offset = DpOffset(0.dp, -48.dp)
                    ) {
                        options.forEach{ option ->
                            DropdownMenuItem(
                                text = { Text(text = option)},
                                onClick = {
                                    selectedMenuItem = option
                                    //Log.d("Dialog", "option is: "+option)
                                    //Log.d("Dialog", "selectedmenuItem is: "+selectedMenuItem)
                                    expandedList = false
                                })
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    // Choose Status
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
                ElevatedButton(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.addToDoElement(
                                ToDoElement(
                                    selectedMenuItem,
                                    selectedMenuItemStatus,
                                    text.text,
                                    ""
                                )
                            )
                        }
                        onDismissRequest.invoke()
                    }
                ) {
                    Text("Add ToDoElement")
                }
            }
        }

    }
    // Make the Keyboard pop Up if the Dialog is called
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}