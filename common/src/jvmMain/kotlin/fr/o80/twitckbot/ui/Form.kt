package fr.o80.twitckbot.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.o80.twitckbot.values.betweenFieldsPadding
import fr.o80.twitckbot.values.screenPadding

@Composable
@Suppress("FunctionName")
fun Form(content: @Composable ColumnScope.() -> Unit) {
    ScrollableColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(betweenFieldsPadding),
        modifier = Modifier.fillMaxSize().padding(screenPadding),
        content = content
    )
}
