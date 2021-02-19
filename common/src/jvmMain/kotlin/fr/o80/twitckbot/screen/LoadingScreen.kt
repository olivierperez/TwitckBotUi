package fr.o80.twitckbot.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.o80.twitckbot.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


@Composable
@Suppress("FunctionName")
fun LoadingScreen(application: Application) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

    runBlocking(Dispatchers.IO) {
        application.connect()
        application.askActions()
    }
}
