package cat.itb.m78.exercices.navDest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.camera.CameraScreen
import cat.itb.m78.exercices.editMarkerScreen.EditMarkerScreen
import cat.itb.m78.exercices.markerInfoScreen.MarkerInfoScreen
import cat.itb.m78.exercices.listScreen.ListScreen
import cat.itb.m78.exercices.mapScreen.MapScreen
import cat.itb.m78.exercices.permissionScreen.PermissionsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.PermissionsScreen) {
        composable<Destination.PermissionsScreen> {
            PermissionsScreen(navigateToMapScreen = { navController.navigate(Destination.MapScreen) })
        }
        composable<Destination.MapScreen> {
            MapScreen(navigateToListScreen = { navController.navigate(Destination.ListScreen)}, navigateToEditMarkerScreen = {navController.navigate(Destination.EditMarkerScreen(it))})
        }
        composable<Destination.ListScreen> {
            ListScreen(navigateToMapScreen = { navController.navigate(Destination.MapScreen) }, navigateToMarkerInfo = {navController.navigate(Destination.MarkerInfoScreen(it))})
        }
        composable<Destination.MarkerInfoScreen> {
            val id = it.toRoute<Destination.MarkerInfoScreen>().id
            MarkerInfoScreen(navigateToMapScreen = {navController.navigate(Destination.MapScreen)}, navigateToListScreen = {navController.navigate(Destination.ListScreen)}, navigateToEditMarkerScreen = {navController.navigate(Destination.EditMarkerScreen(it))}, id = id)
        }
        composable<Destination.EditMarkerScreen> {
            val id = it.toRoute<Destination.EditMarkerScreen>().id
            EditMarkerScreen(navigateToListScreen = {navController.navigate(Destination.ListScreen)}, id = id, navigateToCameraScreen = {navController.navigate(Destination.CameraScreen(it))})
        }
        composable<Destination.CameraScreen> {
            val id = it.toRoute<Destination.CameraScreen>().id
            CameraScreen(navigateToList = { navController.navigate(Destination.ListScreen) }, id = id)
        }
    }
}
