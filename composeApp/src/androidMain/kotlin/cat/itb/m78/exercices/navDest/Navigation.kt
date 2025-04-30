package cat.itb.m78.exercices.navDest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            MapScreen(navigateToMapScreen = { navController.navigate(Destination.MapScreen) }, navigateToListScreen = { navController.navigate(Destination.ListScreen)})
        }
        composable<Destination.ListScreen> {
            ListScreen(navigateToMapScreen = { navController.navigate(Destination.MapScreen) }, navigateToListScreen = { navController.navigate(Destination.ListScreen)})
        }
    }
}