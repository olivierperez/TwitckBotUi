package fr.o80.twitckbot.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Config(
    val actions: List<RemoteAction>,
    val scenes: List<Scene>
)
