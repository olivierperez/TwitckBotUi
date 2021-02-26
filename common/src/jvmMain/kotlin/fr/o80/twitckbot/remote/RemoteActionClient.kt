package fr.o80.twitckbot.remote

import com.squareup.moshi.Moshi
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicReference

typealias Callback<T> = (T) -> Unit

data class Message(
    val content: String
)

class RemoteActionClient(
    private val host: String,
    private val port: Int,
    private val path: String,
    private val onError: (Exception) -> Unit
) {

    private val client = HttpClient(CIO).config { install(WebSockets) }

    private var onActions: AtomicReference<Callback<List<RemoteAction>>> = AtomicReference {}
    private var onScenes: AtomicReference<Callback<List<Scene>>> = AtomicReference {}
    private var onStatus: AtomicReference<Callback<Status>> = AtomicReference {}

    private val moshi = Moshi.Builder().build()

    private val outgoingQueue = LinkedBlockingDeque<Message>()

    suspend fun connect() {
        GlobalScope.launch {
            try {
                client.webSocket(host = host, port = port, path = path) {
                    val output = launch(Dispatchers.IO) { sendMessages() }
                    val input = launch(Dispatchers.IO) { listenToWebSocket() }

                    output.join()
                    input.cancelAndJoin()
                }
            } catch(e: Exception) {
                onError(e)
            }
        }
    }

    private suspend fun DefaultClientWebSocketSession.sendMessages() {
        while (true) {
            when (val message = outgoingQueue.take().content) {
                "Close" -> {
                    println("Closing")
                    return
                }
                else -> {
                    println("Sending message: $message")
                    send(Frame.Text(message))
                }
            }
        }
    }

    private suspend fun DefaultClientWebSocketSession.listenToWebSocket() {
        while (true) {
            when (val received = incoming.receive()) {
                is Frame.Text -> {
                    val receivedText = received.readText()
                    println("Server said: $receivedText")
                    when {
                        receivedText.startsWith("Config:") ->
                            handleConfig(receivedText.substring(7))
                        receivedText.startsWith("Status:") ->
                            handleStatus(receivedText.substring(7))
                    }
                }
                is Frame.Ping -> {
                    println("Ping")
                    send(Frame.Pong(received.buffer))
                }
            }
        }
    }

    private fun handleStatus(statusJson: String) {
        val adapter = moshi.adapter(Status::class.java)
        val status = adapter.fromJson(statusJson)!!
        onStatus.get()(status)
    }

    private fun handleConfig(configJson: String) {
        val adapter = moshi.adapter(Config::class.java)
        val config: Config = adapter.fromJson(configJson)!!
        onActions.get()(config.actions)
        onScenes.get()(config.scenes)
    }

    fun doOnActions(block: Callback<List<RemoteAction>>) {
        onActions.set(block)
    }

    fun doOnScenes(block: Callback<List<Scene>>) {
        onScenes.set(block)
    }

    fun doOnStatus(block: Callback<Status>) {
        onStatus.set(block)
    }

    fun requestConfig() {
        outgoingQueue.offer(Message("GetConfig"))
    }

    fun disconnect() {
        outgoingQueue.offer(Message("Close"))
    }

    fun sendCommand(command: String) {
        outgoingQueue.offer(Message(command))
    }

    fun newAction(name: String, execute: String) {
        val action = RemoteAction(name, "fallback.png", execute)
        val adapter = moshi.adapter(RemoteAction::class.java)
        outgoingQueue.offer(Message("AddAction:" + adapter.toJson(action)))
    }
}
