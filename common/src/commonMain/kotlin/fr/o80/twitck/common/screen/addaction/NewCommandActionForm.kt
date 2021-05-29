package fr.o80.twitck.common.screen.addaction

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import fr.o80.twitck.common.Application
import fr.o80.twitckbot.ui.Field
import fr.o80.twitckbot.ui.Form
import fr.o80.twitckbot.ui.TextField

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
