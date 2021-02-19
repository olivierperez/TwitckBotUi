package fr.o80.twitckbot.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import fr.o80.twitckbot.remote.RemoteAction
import fr.o80.twitckbot.ui.ActionButton
import fr.o80.twitckbot.values.screenPadding
import fr.o80.twitckbot.Application

@Composable
@Suppress("FunctionName")
fun ActionsListScreen(
    application: Application
) {
    ScrollableColumn(
        modifier = Modifier.fillMaxSize().padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Actions(application)

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.size(96.dp),
                shape = MaterialTheme.shapes.large,
                onClick = { application.goToAddAction() }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        colorFilter = ColorFilter.tint(colors.primary)
                    )
                }
            }
        }
    }
}

@Composable
@Suppress("FunctionName")
fun Actions(application: Application) {
    application.actions.value.chunked(8)
        .forEach { actions -> ActionsRow(application, actions) }
}

@Composable
@Suppress("FunctionName")
fun ActionsRow(application: Application, actions: List<RemoteAction>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        actions.forEach { action ->
            ActionButton(action) {
                application.send(action.execute)
            }
        }
    }
}
