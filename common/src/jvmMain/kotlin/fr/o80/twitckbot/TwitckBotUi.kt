package fr.o80.twitckbot

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.o80.twitckbot.Screen.*
import fr.o80.twitckbot.screen.ActionsListScreen
import fr.o80.twitckbot.screen.AddActionFormScreen
import fr.o80.twitckbot.screen.ErrorScreen
import fr.o80.twitckbot.screen.LoadingScreen
import fr.o80.twitckbot.values.brightYellow
import fr.o80.twitckbot.values.darkPurple
import fr.o80.twitckbot.values.darkRed
import fr.o80.twitckbot.values.typography

@Composable
@Suppress("FunctionName")
fun TwitckBotUi() {
    val application = ApplicationImpl("192.168.0.5", 9013)

    MaterialTheme(
        colors = darkColors(
            primary = Color(0xFFEFA600),
            onPrimary = Color(0xFFFFF9D9),
            surface = Color(0xFF080111),
            onSurface = Color(0xFFFFF9D9),
        ),
        typography = typography
    ) {
        println("Screen: ${application.screen.value}")
        Be exhaustive when (val screen = application.screen.value) {
            Loading -> LoadingScreen(application)
            Actions -> ActionsListScreen(application)
            AddForm -> AddActionFormScreen(application)
            is Error -> ErrorScreen(application, screen)
        }
    }
}
