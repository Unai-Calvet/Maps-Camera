package cat.itb.m78.exercices.navDest

import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    data object PermissionsScreen
    @Serializable
    data object MapScreen
    @Serializable
    data object ListScreen
}