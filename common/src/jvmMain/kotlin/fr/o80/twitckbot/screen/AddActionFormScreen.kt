package fr.o80.twitckbot.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import fr.o80.twitckbot.Application
import fr.o80.twitckbot.ui.Field
import fr.o80.twitckbot.ui.Form
import fr.o80.twitckbot.ui.TextField

@Composable
@Suppress("FunctionName")
fun AddActionFormScreen(application: Application) {
    val form = mutableStateOf(FormType.COMMAND)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New action",
                        style = MaterialTheme.typography.h5
                    )
                },
                navigationIcon = {
                    Button(onClick = {
                        application.goToActions()
                    }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        Column {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Image(imageVector = Icons.Default.Info, "Command") },
                    label = { Text("Command") },
                    selected = form.value == FormType.COMMAND,
                    onClick = { form.value = FormType.COMMAND }
                )
                BottomNavigationItem(
                    icon = { Image(imageVector = Icons.Default.Email, "Message") },
                    label = { Text("Message") },
                    selected = form.value == FormType.MESSAGE,
                    onClick = { form.value = FormType.MESSAGE }
                )
                BottomNavigationItem(
                    icon = { Image(imageVector = Icons.Default.List, "Scene") },
                    label = { Text("Scene") },
                    selected = form.value == FormType.SCENE,
                    onClick = { form.value = FormType.SCENE }
                )
            }

            when (form.value) {
                FormType.COMMAND -> NewCommandActionForm(application)
                FormType.MESSAGE -> NewMessageActionForm(application)
                FormType.SCENE -> NewSceneActionForm(application)
            }
        }
    }

}

private enum class FormType {
    COMMAND, MESSAGE, SCENE
}

@Composable
@Suppress("FunctionName")
fun NewCommandActionForm(application: Application) {
    val name = Field { it.text.isNotBlank() }
    val command = Field { it.text.isNotBlank() && it.text != "!" }

    fun onSubmitCommand() {
        val nameIsValid = name.checkValidity()
        val commandIsValid = command.checkValidity()

        if (nameIsValid && commandIsValid) {
            application.newCommandAction(name.value.text, command.value.text)
        }
    }

    Form {
        TextField("Name", name, ImeAction.Next, maxLines = 1)
        TextField("!Command", command, ImeAction.Done, maxLines = 1) { onSubmitCommand() }
        OutlinedButton(onClick = { onSubmitCommand() }) { Text("Add") }
    }
}

@Composable
@Suppress("FunctionName")
fun NewMessageActionForm(application: Application) {
    val name = Field { it.text.isNotBlank() }
    val message = Field { it.text.isNotBlank() }

    fun onSubmitMessage() {
        val nameIsValid = name.checkValidity()
        val messageIsValid = message.checkValidity()

        if (nameIsValid && messageIsValid) {
            application.newMessageAction(name.value.text, message.value.text)
        }
    }

    Form {
        TextField("Name", name, ImeAction.Next, maxLines = 1)
        TextField("Message", message, ImeAction.Done, maxLines = -1) { onSubmitMessage() }
        OutlinedButton(onClick = { onSubmitMessage() }) { Text("Add") }
    }
}

@Composable
@Suppress("FunctionName")
fun NewSceneActionForm(application: Application) {
    val name = Field { it.text.isNotBlank() }
    val scene = remember { mutableStateOf(application.scenes.value.first()) }

    fun onSubmitMessage() {
        val nameIsValid = name.checkValidity()

        if (nameIsValid) {
            application.newSceneAction(name.value.text, scene.value)
        }
    }

    Form {
        TextField("Name", name, ImeAction.Next, maxLines = 1)
        Radio(application.scenes.value, transform = { it.name }, onChange = { scene.value = it })
        OutlinedButton(onClick = { onSubmitMessage() }) { Text("Add") }
    }
}

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
