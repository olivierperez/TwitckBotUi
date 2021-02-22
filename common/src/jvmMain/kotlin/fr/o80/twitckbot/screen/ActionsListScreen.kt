package fr.o80.twitckbot.screen

import androidx.compose.foundation.Image
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
import fr.o80.twitckbot.Application
import fr.o80.twitckbot.ui.ActionButton
import fr.o80.twitckbot.ui.FlowLayout
import fr.o80.twitckbot.values.screenPadding

@Composable
@Suppress("FunctionName")
fun ActionsListScreen(
    application: Application
) {
    FlowLayout(
        modifier = Modifier.fillMaxSize(),
        arrangement = Arrangement.spacedBy(4.dp)
    ) {
        application.actions.value.forEach { action ->
            ActionButton(action) {
                application.send(action.execute)
            }
        }

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
