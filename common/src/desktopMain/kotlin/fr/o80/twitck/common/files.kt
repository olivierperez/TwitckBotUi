package fr.o80.twitck.common

import java.io.File

private const val FILES_DIR = ".files"

actual fun fileExists(filename: String): Boolean {
    return File(FILES_DIR, filename).isFile
}

actual fun writeToFile(filename: String, data: ByteArray) {
    File(FILES_DIR).mkdirs()
    File(FILES_DIR, filename).writeBytes(data)
}

actual fun readFromFile(filename: String): ByteArray {
    return File(FILES_DIR, filename).readBytes()
}
