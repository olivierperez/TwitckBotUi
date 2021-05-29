package fr.o80.twitck.common.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        )  {
            Column(
                modifier = Modifier.padding(screenPadding).align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(betweenFieldsPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Error", style = typography.h1)

                Spacer(Modifier.size(32.dp))

                Text(
                    text = "An error occurred:\n\n" + error.exception.message,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.size(32.dp))

                OutlinedButton(
                    onClick = { application.refresh() },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("RETRY", style = typography.body1)
                }
            }
        }
    }
}