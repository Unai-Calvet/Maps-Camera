package cat.itb.m78.exercices.navDest

import kotlinx.serialization.Serializable

object Destination {

    @Serializable
    data object PermissionsScreen
    @Serializable
    data object MapScreen
    @Serializable
    data object ListScreen
    @Serializable
    data class MarkerInfoScreen(val id: Long)
    @Serializable
    data class EditMarkerScreen(val id: Long)
    @Serializable
    data class CameraScreen(val id: Long)

}