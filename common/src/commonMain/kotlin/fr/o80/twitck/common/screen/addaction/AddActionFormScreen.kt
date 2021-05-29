package fr.o80.twitck.common.screen.addaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import fr.o80.twitck.common.Application

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
