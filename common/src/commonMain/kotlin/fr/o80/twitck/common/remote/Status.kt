package fr.o80.twitck.common.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    val currentSceneId: String
)
