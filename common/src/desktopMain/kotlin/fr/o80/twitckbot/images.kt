package fr.o80.twitckbot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
actual fun imageResource(res: String): ImageBitmap =
    androidx.compose.ui.res.imageResource(res)
