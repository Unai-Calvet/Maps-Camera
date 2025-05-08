package cat.itb.m78.exercices.listScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.db.Marker
import cat.itb.m78.exercices.db.database

class ListScreenViewModel : ViewModel() {
    private val markersTable = database.markerQueries
    val  markers = mutableStateOf(getAllMarkers())

    fun getAllMarkers() : List<Marker> {
        return markersTable.readAll().executeAsList()
    }

}