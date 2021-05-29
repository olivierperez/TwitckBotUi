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
