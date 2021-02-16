package fr.o80.twitckbot

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
@Suppress("FunctionName")
fun Counter() {
    val count = remember { mutableStateOf(0) }
    Button(
        onClick = {
            count.value++
        }
    ) {
        Text("Click ou bien ? ${count.value}")
    }
}