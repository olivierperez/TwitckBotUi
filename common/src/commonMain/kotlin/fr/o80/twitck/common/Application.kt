package fr.o80.twitck.common

import androidx.compose.runtime.MutableState
import fr.o80.twitck.common.remote.RemoteAction
import fr.o80.twitck.common.remote.Scene
import fr.o80.twitck.common.remote.Status

interface Application {
    val actions: MutableState<List<RemoteAction>>
    val scenes: MutableState<List<Scene>>
    val screen: MutableState<Screen>
    val status: MutableState<Status>

    suspend fun connect()
    fun askActions()
    fun disconnect()
    fun newCommandAction(label: String, command: String)
    fun newMessageAction(label: String, message: String)
    fun newSceneAction(label: String, scene: Scene)
    fun send(content: String)

    fun goToAddAction()
    fun goToActions()
    fun refresh()
}

sealed class Screen {
    object Actions : Screen() {
        override fun toString(): String = "Screen.Action"
    }
    object AddForm : Screen(){
        override fun toString(): String = "Screen.AddForm"
    }
    object Loading : Screen(){
        override fun toString(): String = "Screen.Loading"
    }
    class Error(val exception: Exception) : Screen(){
        override fun toString(): String = "Screen.Error(${exception.javaClass.simpleName})"
    }
}
