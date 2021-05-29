package fr.o80.twitck.common

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource

@Composable
actual fun imageResource(res: String, fallback: String): ImageBitmap {
    return try {
        val data = readFromFile(res)
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        bitmap.asImageBitmap()
    } catch (e: Exception) {
        ImageBitmap.imageResource(
            try {
                drawableId(res)
            } catch (e: Exception) {
                drawableId(fallback)
            }
        )
    }
}

// TODO: improve resource loading
fun drawableId(res: String): Int {
    val imageName = res.substringAfterLast("/").substringBeforeLast(".")
    val drawableClass = R.drawable::class.java
    val field = drawableClass.getDeclaredField(imageName)
    return field.get(drawableClass) as Int
}
