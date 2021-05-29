package fr.o80.twitck.common

expect fun fileExists(filename: String): Boolean
expect fun writeToFile(filename: String, data: ByteArray)
expect fun readFromFile(filename: String): ByteArray
