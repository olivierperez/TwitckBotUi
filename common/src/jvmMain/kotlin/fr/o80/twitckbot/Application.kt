package fr.o80.twitckbot

import androidx.compose.runtime.MutableState
import fr.o80.twitckbot.remote.RemoteAction
import fr.o80.twitckbot.remote.Scene

interface Application {
    val actions: MutableState<List<RemoteAction>>
    val scenes: MutableState<List<Scene>>
    val screen: MutableState<Screen>

    fun askActions()
    fun disconnect()
    fun newCommandAction(label: String, command: String)
    fun newMessageAction(label: String, message: String)
    fun newSceneAction(label: String, scene: Scene)
    fun send(content: String)

    fun goToAddAction()
    fun goToActions()
}

enum class Screen {
    ACTIONS,
    ADD_FORM,
    LOADING
}
