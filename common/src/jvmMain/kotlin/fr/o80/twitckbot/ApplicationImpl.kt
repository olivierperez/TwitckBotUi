package fr.o80.twitckbot

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import fr.o80.twitckbot.remote.RemoteAction
import fr.o80.twitckbot.remote.RemoteActionClient
import fr.o80.twitckbot.remote.Scene

class ApplicationImpl(
    hostname: String,
    port: Int
) : Application {

    private val client = RemoteActionClient(hostname, port, "/actions")

    override val actions: MutableState<List<RemoteAction>> = mutableStateOf(emptyList())
    override val scenes: MutableState<List<Scene>> = mutableStateOf(emptyList())
    override val screen: MutableState<Screen> = mutableStateOf(Screen.LOADING)

    init {
        client.connect()

        client.doOnActions { actions ->
            this.actions.value = actions
            if (screen.value == Screen.LOADING) {
                screen.value = Screen.ACTIONS
            }
        }

        client.doOnScenes { scenes ->
            this.scenes.value = scenes
        }
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
        screen.value = Screen.ACTIONS
    }

    override fun newMessageAction(label: String, message: String) {
        client.newAction(label, "Message:$message")
        screen.value = Screen.ACTIONS
    }

    override fun newSceneAction(label: String, scene: Scene) {
        client.newAction(label, "Scene:${scene.id}")
        screen.value = Screen.ACTIONS
    }

    override fun send(content: String) {
        client.sendCommand(content)
    }

    override fun goToAddAction() {
        screen.value = Screen.ADD_FORM
    }

    override fun goToActions() {
        screen.value = Screen.ACTIONS
    }
}
