package fr.o80.twitckbot.desktop

import androidx.compose.desktop.Window
import androidx.compose.ui.unit.IntSize
import fr.o80.twitckbot.Counter
import javax.swing.SwingUtilities.invokeLater

fun main() {
    invokeLater {
        Window(
            title = "TwitckBot",
            size = IntSize(1280, 768)
        ) {
            Counter()
        }
    }
}