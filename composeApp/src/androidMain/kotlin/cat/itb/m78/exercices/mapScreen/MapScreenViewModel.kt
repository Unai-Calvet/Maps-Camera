package cat.itb.m78.exercices.mapScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.Marker
import cat.itb.m78.exercices.db.database

class MapScreenViewModel : ViewModel() {
    private val markersTable = database.markerQueries
    val  markers = mutableStateOf(getAllMarkers())


    fun getAllMarkers() : List<Marker> {
        return markersTable.readAll().executeAsList()
    }

    fun addMarker(lati: Double, long: Double, navigateToEditMarker : (Long) -> Unit) {
        // Examen recuperació
        val la = {lati - 0.01}.toString()
        val lo = long.toString()
        markersTable.create(la, lo)
        //
        val id = markersTable.readIdByLatLng(la, lo).executeAsOne()
        navigateToEditMarker(id)
    }
}