package fr.o80.twitck.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun imageResource(res: String, fallback: String): ImageBitmap
