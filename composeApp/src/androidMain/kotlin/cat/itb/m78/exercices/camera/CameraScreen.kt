package cat.itb.m78.exercices.camera

import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.database
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

@Composable

fun CameraScreen(navigateToList: () -> Unit, id: Long){
    val settings : Settings = Settings()
    val counter = settings.get("counter", 0)
    val marker = database.markerQueries.readById(id).executeAsOne()
    val viewModel = viewModel{ CameraViewModel() }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }
    val surfaceRequest = viewModel.surferRequest.value
    surfaceRequest?.let { request ->
        CameraXViewfinder(
            surfaceRequest = request,
            modifier = Modifier.fillMaxSize()
        )
        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            Button(onClick = { viewModel.takePhoto(context, navigateToList, marker)}){
                Text("Fer foto, $counter fotos fetes anteriorment")
            }
            Spacer(modifier = Modifier.size(25.dp))
            Button(onClick = { navigateToList() }){
                Text("Cancelar")
            }
        }
    }
}