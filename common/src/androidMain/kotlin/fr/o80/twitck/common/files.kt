package fr.o80.twitck.common

import android.content.Context
import java.io.File

lateinit var _Context: Context

actual fun fileExists(filename: String): Boolean {
    val fileInCache = File(_Context.cacheDir, filename)
    return fileInCache.exists()
}

actual fun writeToFile(filename: String, data: ByteArray) {
    val fileInCache = File(_Context.cacheDir, filename)
    fileInCache.outputStream().buffered().use { it.write(data) }
    println("$filename saved!")
}

actual fun readFromFile(filename: String): ByteArray {
    val fileInCache = File(_Context.cacheDir, filename)
    return fileInCache.inputStream().buffered().use {
        it.readBytes()
    }
}
