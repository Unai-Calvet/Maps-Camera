package cat.itb.m78.exercices.mapScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.Marker
import cat.itb.m78.exercices.db.database

class MapScreenViewModel : ViewModel() {
    private val markersTable = database.markerQueries
    val  markers = getAllMarkers()


    fun getAllMarkers() : List<Marker> {
        return markersTable.readAll().executeAsList()
    }

    fun addMarker(lati: String, long: String, navigateToEditMarker : (Long) -> Unit) {
        // Examen recuperació
        val la = lati
        val lo = long
        markersTable.create(la, lo)
        //
        val id = markersTable.readIdByLatLng(la, lo).executeAsOne()
        navigateToEditMarker(id)
    }
}