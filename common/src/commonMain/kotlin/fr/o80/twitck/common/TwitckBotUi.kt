package fr.o80.twitck.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.o80.twitck.common.Screen.*
import fr.o80.twitck.common.screen.ActionsListScreen
import fr.o80.twitck.common.screen.ErrorScreen
import fr.o80.twitck.common.screen.LoadingScreen
import fr.o80.twitck.common.screen.addaction.AddActionFormScreen
import fr.o80.twitck.common.values.typography

@Composable
@Suppress("FunctionName")
fun TwitckBotUi() {
    val application = ApplicationImpl("192.168.0.5", 9013)

    MaterialTheme(
        colors = darkColors(
            primary = Color(0xFFEFA600),
            onPrimary = Color(0xFF080111),
            surface = Color(0xFF220632),
            onSurface = Color(0xFFFFF9D9),
            background = Color(0xFF220632),
            onBackground = Color(0xFFEFA600)
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
