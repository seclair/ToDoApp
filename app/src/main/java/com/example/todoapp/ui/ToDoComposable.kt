package com.example.todoapp.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.ToDoElement
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ClickableToDoCard(viewModel: ToDoViewModel, element: ToDoElement, modifier: Modifier = Modifier){
    ToDoCard(viewModel = viewModel, element = element, modifier = modifier) {
        Log.d("Room", "called .onClick in ClickableToDoCard")
    }
}

@Composable
fun ToDoCard(viewModel: ToDoViewModel, element: ToDoElement, modifier: Modifier = Modifier, onClick: () -> Unit){
    // for coroutines
    val coroutineScope = rememberCoroutineScope()
    // for dragging action
    var offsetX by remember { mutableStateOf(0f) }
    var cardWidth by remember { mutableStateOf(0) }
    var alphaValues by remember { mutableStateOf(Pair(0f,1f)) }
    Box(modifier = Modifier){
        Card(
            modifier = Modifier
                .matchParentSize()
                .padding(4.dp)
                .alpha(alphaValues.first),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimaryContainer)
        ){
            Box(contentAlignment = Alignment.Center, content = { Text(
                text = " Finished!",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.background,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight().padding(4.dp).align(Alignment.Center)
            )})
        }
        ElevatedCard(
            Modifier
                .fillMaxWidth()
                .onSizeChanged { size -> cardWidth = size.width }
                .padding(4.dp)
                .alpha(alphaValues.second)
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                        //isDragging = offsetX > cardWidth * 0.1
                        alphaValues = getAlpha(offsetX, cardWidth)
                    },
                    onDragStopped = {
                        if (offsetX > 0.6f * cardWidth) {
                            coroutineScope.launch {
                                viewModel.deleteToDoElement(element)
                            }
                        }
                        offsetX = 0f
                        alphaValues = Pair(0f, 1f)
                    },
                    orientation = Orientation.Horizontal
                )
                .clickable { onClick() },
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
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
}

// Calculate Alpha Values for swiping action
fun getAlpha(x: Float, width: Int ): Pair<Float, Float>{
    val colorBackAlpha: Float = ( 0.8f * x / width - 0.2f ).coerceAtMost(1f)
    val colorCardAlpha: Float = ( 1 - 0.6f * x / width ).coerceAtMost(1f)
    return Pair(colorBackAlpha, colorCardAlpha)
}

/*@Preview
@Composable
private fun ToDoCardPreview(){
    //val dummyElement = ToDoElement("Beilspiel Liste",0,"Ein Beispiel ToDof, could be a longer title too.","Nearly no description, but we could write a lot in here. A LOT! And some more, because this is a decription-.")
    //ToDoCard(element = dummyElement){
    //}
}*/
