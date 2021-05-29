package fr.o80.twitck.common

import android.content.Context

lateinit var _Context: Context

actual fun fileExists(filename: String): Boolean {
    return try {
        _Context.openFileInput(filename).close()
        true
    } catch (e: Exception) {
        try {
            drawableId(filename)
            true
        } catch (e: Exception) {
            false
        }
    }
}

actual fun writeToFile(filename: String, data: ByteArray) {
    val outputStream = _Context.openFileOutput(filename, Context.MODE_PRIVATE)
    outputStream.buffered().use {
        it.write(data)
    }
    println("$filename saved!")
}

actual fun readFromFile(filename: String): ByteArray {
    val inputStream = _Context.openFileInput(filename)
    return inputStream.buffered().use {
        it.readBytes()
    }
}
