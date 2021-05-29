package fr.o80.twitckbot.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material.TextField as ComposeTextField

class Field(
    initialContent: String = "",
    val validate: (TextFieldValue) -> Boolean = { true }
) {
    val valueState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(initialContent))
    val errorState: MutableState<Boolean> = mutableStateOf(false)

    val value: TextFieldValue get() = valueState.value

    fun checkValidity(): Boolean {
        return validate(valueState.value).also { valid ->
            errorState.value = !valid
        }
    }

    fun set(content: TextFieldValue) {
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
    ComposeTextField(
        value = field.valueState.value,
        onValueChange = { fieldValue -> field.set(fieldValue) },
        isError = field.errorState.value,
        placeholder = { Text(placeholder) },
        maxLines = if (maxLines > 0) maxLines else Int.MAX_VALUE,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = action),
//        onImeAction = { imeAction, _ -> if (imeAction == action) onImeAction() }
    )
}
