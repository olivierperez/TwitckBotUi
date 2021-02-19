package fr.o80.twitckbot

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.o80.twitckbot.screen.ActionsListScreen
import fr.o80.twitckbot.screen.AddActionFormScreen
import fr.o80.twitckbot.screen.LoadingScreen
import fr.o80.twitckbot.values.brightYellow
import fr.o80.twitckbot.values.darkPurple
import fr.o80.twitckbot.values.darkRed

@Composable
@Suppress("FunctionName")
fun TwitckBotUi() {
    val application = ApplicationImpl("192.168.0.5", 9013)

    MaterialTheme(
        colors = lightColors(
            primary = Color.darkRed,
            onPrimary = Color.brightYellow,
            secondary = Color.darkPurple,
            onSecondary = Color.LightGray
        )
    ) {
        println("Screen: ${application.screen.value}")
        when (application.screen.value) {
            Screen.LOADING -> LoadingScreen(application)
            Screen.ACTIONS -> ActionsListScreen(application)
            Screen.ADD_FORM -> AddActionFormScreen(application)
        }
    }
}
