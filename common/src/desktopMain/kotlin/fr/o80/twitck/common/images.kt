package fr.o80.twitck.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.imageFromResource

@Composable
actual fun imageResource(res: String, fallback: String): ImageBitmap {
    return remember(res) {
        try {
            imageFromResource(res)
        } catch (e: Exception) {
            imageFromResource(fallback)
        }
    }
}

