package fr.o80.twitckbot

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import fr.o80.twitckbot.remote.RemoteAction
import fr.o80.twitckbot.remote.RemoteActionClient
import fr.o80.twitckbot.remote.Scene
import fr.o80.twitckbot.remote.Status

class ApplicationImpl(
    hostname: String,
    port: Int
) : Application {

    private val client = RemoteActionClient(hostname, port, "/actions", ::goToError)

    override val actions: MutableState<List<RemoteAction>> = mutableStateOf(emptyList())
    override val scenes: MutableState<List<Scene>> = mutableStateOf(emptyList())
    override val screen: MutableState<Screen> = mutableStateOf(Screen.Loading)
    override val status: MutableState<Status> = mutableStateOf(Status(""))

    init {
        client.doOnActions { actions ->
            this.actions.value = actions
            if (screen.value == Screen.Loading) {
                screen.value = Screen.Actions
            }
            downloadMissingImages(actions)
        }

        client.doOnScenes { scenes ->
            this.scenes.value = scenes
        }

        client.doOnStatus { status ->
            this.status.value = status
        }
    }

    private fun downloadMissingImages(actions: List<RemoteAction>) {
        actions.map { it.image }
            .filterNot { fileExists(it) }
            .distinct()
            .forEach {
                println("Asking image to server: $it")
                client.sendCommand("GetImage:$it")
            }
    }

    override suspend fun connect() {
        client.connect()
    }

    override fun askActions() {
        client.requestConfig()
    }

    override fun disconnect() {
        client.disconnect()
    }

    override fun newCommandAction(label: String, command: String) {
        if (command.startsWith('!')) {
            client.newAction(label, "Command:$command")
        } else {
            client.newAction(label, "Command:!$command")
        }
        screen.value = Screen.Actions
    }

    override fun newMessageAction(label: String, message: String) {
        client.newAction(label, "Message:$message")
        screen.value = Screen.Actions
    }

    override fun newSceneAction(label: String, scene: Scene) {
        client.newAction(label, "Scene:${scene.id}")
        screen.value = Screen.Actions
    }

    override fun send(content: String) {
        client.sendCommand(content)
    }

    override fun goToAddAction() {
        screen.value = Screen.AddForm
    }

    override fun goToActions() {
        screen.value = Screen.Actions
    }

    private fun goToError(e: Exception) {
        screen.value = Screen.Error(e)
    }

    override fun refresh() {
        screen.value = Screen.Loading
    }

}
