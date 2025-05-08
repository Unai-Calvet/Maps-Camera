package cat.itb.m78.exercices.markerInfoScreen

import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.Marker
import cat.itb.m78.exercices.db.database

class MarkerInfoScreenViewModel : ViewModel() {
    private val markersTable = database.markerQueries

    fun getMarker(id: Long) : Marker {
        return markersTable.readById(id).executeAsOne()
    }
}