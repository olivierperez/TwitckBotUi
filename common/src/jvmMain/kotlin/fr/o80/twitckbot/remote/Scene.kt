package fr.o80.twitckbot.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Scene(
    val id: String,
    val name: String
)
