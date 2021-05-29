package fr.o80.twitck.common.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Config(
    val actions: List<RemoteAction>,
    val scenes: List<Scene>
)
