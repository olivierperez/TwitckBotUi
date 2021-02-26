package fr.o80.twitckbot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
actual fun imageResource(res: String, fallback: String): ImageBitmap = try {
    androidx.compose.ui.res.imageResource(res)
} catch (e: Exception) {
    androidx.compose.ui.res.imageResource(fallback)
}

