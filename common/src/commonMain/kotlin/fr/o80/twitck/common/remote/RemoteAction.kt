package fr.o80.twitck.common.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteAction(
    val name: String,
    val image: String,
    val execute: String
)
