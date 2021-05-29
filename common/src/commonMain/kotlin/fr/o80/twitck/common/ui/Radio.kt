package fr.o80.twitckbot.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Suppress("FunctionName")
fun <T> Radio(
    items: List<T>,
    initiallySelected: T = items.first(),
    transform: (T) -> String,
    onChange: (T) -> Unit
) {
    var selected by remember { mutableStateOf(initiallySelected) }

    fun select(item: T) {
        onChange(item)
        selected = item
    }

    Column {
        items.forEach { item ->
            Row {
                RadioButton(
                    selected = selected == item,
                    onClick = {
                        select(item)
                    }
                )
                Text(
                    text = transform(item),
                    modifier = Modifier.clickable(onClick = {
                        select(item)
                    }).padding(start = 4.dp)
                )
            }
        }
    }
}
