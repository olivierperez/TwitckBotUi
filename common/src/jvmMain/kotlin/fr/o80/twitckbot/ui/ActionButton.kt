package fr.o80.twitckbot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.o80.twitckbot.imageResource
import fr.o80.twitckbot.values.textPadding
import fr.o80.twitckbot.remote.RemoteAction

@Composable
@Suppress("FunctionName")
fun ActionButton(action: RemoteAction, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(96.dp),
        shape = MaterialTheme.shapes.large,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                bitmap = imageResource(action.image),
                contentDescription = null,
                modifier = Modifier.size(64.dp).alpha(.5f).align(Alignment.Center)
            )
            Text(
                action.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter).padding(textPadding)
            )
        }
    }
}
