package fr.o80.twitck.desktop

import androidx.compose.desktop.DesktopTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.ui.unit.IntSize
import fr.o80.twitck.common.TwitckBotUi
import fr.o80.twitck.common.values.windowIcon
import javax.swing.SwingUtilities.invokeLater

fun main() {
    invokeLater {
        Window(
            title = "TwitckBotUI",
            icon = windowIcon,
            size = IntSize(1280, 768)
        ) {
            DisableSelection {
                DesktopTheme {
                    TwitckBotUi()
                }
            }
        }
    }
}