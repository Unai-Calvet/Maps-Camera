package cat.itb.m78.exercices.editMarkerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Marker

@Composable
fun EditMarkerScreen(navigateToListScreen: () -> Unit, id : Long) {
    val viewModel = viewModel { EditMarkerScreenViewModel() }
    EditMarkerScreen(navigateToListScreen, viewModel.getMarker(id), viewModel::onClickButton)
}

@Composable
fun EditMarkerScreen(navigateToListScreen: () -> Unit, marker : Marker, onClickButon : (Long, String, String, String, String, () -> Unit) -> Unit) {
    val title = remember { mutableStateOf(marker.title) }
    val imageUri = remember { mutableStateOf(marker.imageUri) }
    val info = remember { mutableStateOf(marker.info) }
    val description = remember { mutableStateOf(marker.description) }

    if (title.value == null) {
        title.value = ""
    }
    if (imageUri.value == null) {
        imageUri.value = ""
    }
    if (info.value == null) {
        info.value = ""
    }
    if (description.value == null) {
        description.value = ""
    }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(100.dp))

        OutlinedTextField(
            title.value!!,
            label = { Text("Títol (deixa-ho en blanc per a eliminar el marcador)")},
            onValueChange = {
                title.value = it
            }
        )

        /*OutlinedTextField(
            imageUri.value!!,
            label = { Text("Image URI")},
            onValueChange = {
                imageUri.value = it
            }
        )*/

        Spacer(modifier = Modifier.size(100.dp))

        OutlinedTextField(
            info.value!!,
            label = { Text("Informació general")},
            onValueChange = {
                info.value = it
            }
        )

        Spacer(modifier = Modifier.size(100.dp))

        OutlinedTextField(
            description.value!!,
            label = { Text("Descripció")},
            onValueChange = {
                description.value = it
            }
        )

        Spacer(modifier = Modifier.size(100.dp))

        Button(onClick = { onClickButon(marker.id, title.value!!, imageUri.value!!, info.value!!, description.value!!, navigateToListScreen) }) {
            Text("Realitzar canvis")
        }

    }
}