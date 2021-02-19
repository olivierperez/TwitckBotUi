package fr.o80.twitckbot.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction

class Field(
    initialContent: String = "",
    val validate: (String) -> Boolean = { true }
) {
    val valueState: MutableState<String> = mutableStateOf(initialContent)
    val errorState: MutableState<Boolean> = mutableStateOf(false)

    val value: String get() = valueState.value

    fun checkValidity(): Boolean {
        return validate(valueState.value).also { valid ->
            errorState.value = !valid
        }
    }

    fun set(content: String) {
        this.valueState.value = content
        this.errorState.value = false
    }
}

@Composable
@Suppress("FunctionName")
fun TextField(
    placeholder: String,
    field: Field,
    action: ImeAction,
    maxLines: Int,
    onImeAction: () -> Unit = {}
) {
    androidx.compose.material.TextField(
        value = field.valueState.value,
        onValueChange = { field.set(it) },
        placeholder = { Text(placeholder) },
        isErrorValue = field.errorState.value,
        keyboardOptions = KeyboardOptions(imeAction = action),
        maxLines = if (maxLines >0 ) maxLines else Int.MAX_VALUE,
        onImeActionPerformed = { imeAction, _ -> if (imeAction == action) onImeAction() }
    )
}
