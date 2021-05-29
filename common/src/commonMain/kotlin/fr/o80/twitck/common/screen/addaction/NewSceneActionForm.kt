package fr.o80.twitck.common.screen.addaction

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import fr.o80.twitck.common.Application
import fr.o80.twitckbot.ui.Field
import fr.o80.twitckbot.ui.Form
import fr.o80.twitckbot.ui.Radio
import fr.o80.twitckbot.ui.TextField

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
