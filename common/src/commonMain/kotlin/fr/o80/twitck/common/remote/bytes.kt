package fr.o80.twitck.common.remote

fun ByteArray.split(separator: ByteArray): Pair<String, ByteArray> {
    val separatorPos = this.findBytes(separator) ?: error("Failed to found separator in binary")
    val filename = String(this.sliceArray(0 until separatorPos.first))
    val data = this.sliceArray(separatorPos.second until this.size)

    return Pair(filename, data)
}

private fun ByteArray.findBytes(searched: ByteArray): Pair<Int, Int>? {
    this.forEachIndexed { index, byte ->
        if (byte == searched[0]) {
            if (searched.indices.all { delta -> this[index + delta] == searched[delta] }) {
                return Pair(index, index + searched.size)
            }
        }
    }
    return null
}
