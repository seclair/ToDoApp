package com.example.todoapp.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.ToDoElement
import kotlinx.coroutines.launch

@Composable
fun ClickableToDoCard(viewModel: ToDoViewModel, element: ToDoElement, modifier: Modifier = Modifier){
    val coroutineScope = rememberCoroutineScope()
    ToDoCard(element = element, modifier = modifier) {
        Log.d("Room", "called .onClick in ClickableToDoCard")
        coroutineScope.launch {
            viewModel.deleteToDoElement(element)
        }
    }
}

@Composable
fun ToDoCard(element: ToDoElement, modifier: Modifier = Modifier, onClick: () -> Unit){
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {onClick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        //shape = RoundedCornerShape(2.dp)
        ){
        Column(Modifier.padding(10.dp)){
            Row(horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = element.title,
                    Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineSmall
                )
                Column(){
                    Text(text = "Status:"+element.status.toString(),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(text = element.listTitle,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Row(){
                Text(text = element.description)
            }
        }
    }
}

@Preview
@Composable
private fun ToDoCardPreview(){
    val dummyElement = ToDoElement("Beilspiel Liste",0,"Ein Beispiel ToDo, could be a longer title too.","Nearly no description, but we could write a lot in here. A LOT! And some more, because this is a decription-.")
    ToDoCard(element = dummyElement){

    }
}
