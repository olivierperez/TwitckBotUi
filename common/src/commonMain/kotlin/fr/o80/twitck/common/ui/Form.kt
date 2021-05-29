package fr.o80.twitckbot.ui

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.o80.twitck.common.values.betweenFieldsPadding
import fr.o80.twitck.common.values.screenPadding

@Composable
@Suppress("FunctionName")
fun Form(content: @Composable ColumnScope.() -> Unit) {
    val scrollableState = remember { ScrollableState { it } }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(betweenFieldsPadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(screenPadding)
            .verticalScroll(state = rememberScrollState()),
        content = content
    )
}
