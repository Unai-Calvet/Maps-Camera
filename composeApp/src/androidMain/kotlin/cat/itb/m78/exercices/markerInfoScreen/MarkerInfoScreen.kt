package cat.itb.m78.exercices.markerInfoScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Marker
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun MarkerInfoScreen(navigateToMapScreen: () -> Unit, navigateToListScreen: () -> Unit, navigateToEditMarkerScreen: (Long) -> Unit, id : Long) {
    val viewModel = viewModel { MarkerInfoScreenViewModel() }
    MarkerInfoScreen(navigateToMapScreen, navigateToListScreen, navigateToEditMarkerScreen, viewModel.getMarker(id))
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MarkerInfoScreen(navigateToMapScreen: () -> Unit, navigateToListScreen: () -> Unit, navigateToEditMarkerScreen: (Long) -> Unit, marker: Marker) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
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
                            selected = false,
                            onClick = { navigateToListScreen() }
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

        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.size(100.dp))

            if (!marker.title.isNullOrEmpty()) {
                OutlinedCard {
                    Text(marker.title)
                }
            }

            Spacer(modifier = Modifier.size(100.dp))

            if (!marker.imageUri.isNullOrEmpty()) {
                AsyncImage(
                    model = marker.imageUri.toUri(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.size(100.dp))

            if (!marker.info.isNullOrEmpty()) {
                OutlinedCard {
                    Text(marker.info)
                }
            }

            Spacer(modifier = Modifier.size(100.dp))

            if (!marker.description.isNullOrEmpty()) {
                OutlinedCard {
                    Text(marker.description)
                }
            }

            Spacer(modifier = Modifier.size(100.dp))

            Button(onClick = {navigateToEditMarkerScreen(marker.id)}) {
                Text("Editar")
            }
        }
    }
}