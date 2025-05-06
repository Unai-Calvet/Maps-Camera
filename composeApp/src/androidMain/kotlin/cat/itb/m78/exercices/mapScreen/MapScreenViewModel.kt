package cat.itb.m78.exercices.mapScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.Marker
import cat.itb.m78.exercices.db.database

class MapScreenViewModel : ViewModel() {
    private val markersTable = database.markerQueries
    val  messages = mutableStateOf(getAllMarkers())


    fun getAllMarkers() : List<Marker> {
        return markersTable.readAll().executeAsList()
    }

    fun addMarker(lat: Float, lon: Float) {

    }
}