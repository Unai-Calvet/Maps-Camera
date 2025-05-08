package cat.itb.m78.exercices.listScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Marker
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun ListScreen(navigateToMapScreen: () -> Unit, navigateToMarkerInfo: (Long) -> Unit) {
    val viewModel = viewModel { ListScreenViewModel() }
    ListScreen(navigateToMapScreen, navigateToMarkerInfo, viewModel.markers.value)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(navigateToMapScreen: () -> Unit, navigateToMarkerInfo: (Long) -> Unit, markers: List<Marker>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Menu", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = { Text("Mapa") },
                        selected = false,
                        onClick = { navigateToMapScreen() }
                    )
                    NavigationDrawerItem(
                        label = { Text("Llista") },
                        selected = true,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )

                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { }
        Column {
            Spacer(modifier = Modifier.size(100.dp))

            for (marker in markers) {
                TextButton(
                    onClick = { navigateToMarkerInfo(marker.id) },
                    shape = RoundedCornerShape(0),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    marker.title?.let { Text(it) }
                }
                Spacer(modifier = Modifier.size(25.dp))
            }
        }
    }
}