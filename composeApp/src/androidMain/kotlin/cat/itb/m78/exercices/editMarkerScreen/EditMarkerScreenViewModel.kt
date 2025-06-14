package cat.itb.m78.exercices.editMarkerScreen

import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.Marker
import cat.itb.m78.exercices.db.database

class EditMarkerScreenViewModel : ViewModel() {
    private val markersTable = database.markerQueries

    fun getMarker(id: Long) : Marker {
        return markersTable.readById(id).executeAsOne()
    }

    fun onClickPhoto(title: String, imageUri: String, info: String, description: String, id: Long, navigateToCamera: (Long) -> Unit) {
        updateMarker(title, imageUri, info, description, id)
        navigateToCamera(id)
    }

    fun updateMarker(title: String, imageUri: String, info: String, description: String, id: Long) {
        markersTable.update(title, imageUri, info, description, id)
    }

    fun deleteMarker(id: Long) {
        markersTable.delete(id)
    }

    fun onClickButton(id: Long, title: String, imageUri: String, info: String, description: String, navigateToListScreen: () -> Unit) {
        if (title.isBlank()) {
            deleteMarker(id)
            navigateToListScreen()
        }
        else {
            updateMarker(title, imageUri, info, description, id)
            navigateToListScreen()
        }
    }
}