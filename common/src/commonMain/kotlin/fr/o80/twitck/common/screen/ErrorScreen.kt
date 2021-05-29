package fr.o80.twitck.common.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.o80.twitck.common.Application
import fr.o80.twitck.common.Screen
import fr.o80.twitck.common.values.betweenFieldsPadding
import fr.o80.twitck.common.values.screenPadding

@Composable
@Suppress("FunctionName")
fun ErrorScreen(application: Application, error: Screen.Error) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(screenPadding).align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(betweenFieldsPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Error", style = typography.h1)

            Spacer(Modifier.size(48.dp))

            Image(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = "An error occurred:\n\n" + error.exception.message,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.size(48.dp))

            TextButton(
                onClick = { application.refresh() }
            ) {
                Text("RETRY", style= typography.body1)
            }
        }
    }
}