package cat.itb.m78.exercices.editMarkerScreen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import com.russhwolf.settings.Settings

@Composable
fun EditMarkerScreen(navigateToListScreen: () -> Unit, id : Long, navigateToCameraScreen : (Long) -> Unit) {
    val viewModel = viewModel { EditMarkerScreenViewModel() }
    EditMarkerScreen(navigateToListScreen, viewModel.getMarker(id), viewModel::onClickButton, navigateToCameraScreen, viewModel::onClickPhoto)
}

@Composable
fun EditMarkerScreen(
    navigateToListScreen: () -> Unit,
    marker : Marker,
    onClickButon : (Long, String, String, String, String, () -> Unit) -> Unit,
    navigateToCameraScreen : (Long) -> Unit,
    onClickPhoto: (String, String, String, String, Long, (Long) -> Unit) -> Unit) {
    val title = remember { mutableStateOf(marker.title) }
    val imageUri = remember { mutableStateOf(marker.imageUri) }
    val info = remember { mutableStateOf(marker.info) }
    val description = remember { mutableStateOf(marker.description) }

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            imageUri.value = uri.toString()
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

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
        Spacer(modifier = Modifier.size(50.dp))

        OutlinedTextField(
            title.value!!,
            label = { Text("Títol (deixa-ho en blanc per a eliminar el marcador)")},
            onValueChange = {
                title.value = it
            }
        )

        Spacer(modifier = Modifier.size(50.dp))

        Button(onClick = {onClickPhoto(title.value!!, imageUri.value!!, info.value!!, description.value!!, marker.id, navigateToCameraScreen)}) {
            Text("Fer Foto")
        }

        Button(onClick = {pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}) {
            Text("Selecionar foto")
        }

        Button(onClick = {
            val settings: Settings = Settings()
            imageUri.value = settings.getString(
                "lastPhotoUri",
                defaultValue = ""
            )
            if (imageUri.value == "") {
                imageUri.value = null
            }
        }) {
            Text("Selecionar ultima foto")
        }

        Spacer(modifier = Modifier.size(50.dp))

        OutlinedTextField(
            info.value!!,
            label = { Text("Informació general")},
            onValueChange = {
                info.value = it
            }
        )

        Spacer(modifier = Modifier.size(50.dp))

        OutlinedTextField(
            description.value!!,
            label = { Text("Descripció")},
            onValueChange = {
                description.value = it
            }
        )

        Spacer(modifier = Modifier.size(50.dp))

        Button(onClick = { onClickButon(marker.id, title.value!!, imageUri.value!!, info.value!!, description.value!!, navigateToListScreen) }) {
            Text("Realitzar canvis")
        }

    }
}