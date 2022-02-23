package fr.o80.twitck.common

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource

@Composable
actual fun imageResource(res: String, fallback: String): ImageBitmap {
    return readImageFromFile(res)
        ?: readImageFromR(res)
        ?: readFromAssets(fallback)
}

private fun readImageFromFile(res: String): ImageBitmap? {
    return try {
        val data = readFromFile(res)
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        bitmap.asImageBitmap()
    } catch (e: Exception) {
        null
    }
}

@Composable
private fun readImageFromR(res: String): ImageBitmap? {
    return drawableId(res)
        ?.let { ImageBitmap.imageResource(it) }
}

private fun readFromAssets(fallback: String): ImageBitmap {
    val bytes = _Context.assets.open(fallback).readBytes()
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    return bitmap.asImageBitmap()
}

// TODO: improve resource loading
fun drawableId(res: String): Int? {
    return try {
        val imageName = res.substringAfterLast("/").substringBeforeLast(".")
        val drawableClass = R.drawable::class.java
        val field = drawableClass.getDeclaredField(imageName)
        field.get(drawableClass) as Int
    } catch (e: Exception) {
        null
    }
}
