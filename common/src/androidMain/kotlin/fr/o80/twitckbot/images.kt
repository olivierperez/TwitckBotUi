package fr.o80.twitckbot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

@Composable
actual fun imageResource(res: String, fallback: String): ImageBitmap {
    val id = try {
        drawableId(res)
    } catch (e: Exception) {
        drawableId(fallback)
    }
    return ImageBitmap.imageResource(id)
}

// TODO: improve resource loading
private fun drawableId(res: String): Int {
    val imageName = res.substringAfterLast("/").substringBeforeLast(".")
    val drawableClass = R.drawable::class.java
    val field = drawableClass.getDeclaredField(imageName)
    return field.get(drawableClass) as Int
}
