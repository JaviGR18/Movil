package com.example.ejemplonav.ui.model

import android.net.Uri

// Representa el estado actual de toda la app en un momento dado
// Las pantallas observan este estado y se actualizan automáticamente cuando cambia
data class AlbumViewState(
    val tempFileUri: Uri? = null,       // URI temporal para guardar la foto de la cámara
    val fallas: List<Falla> = emptyList() // Lista de todas las fallas registradas
)