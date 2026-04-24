package com.example.ejemplonav.ui.model

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// ViewModel es el cerebro de la app — guarda y maneja los datos
// Sobrevive a cambios de pantalla como rotaciones del dispositivo
class AlbumVIewModel : ViewModel() {

    // Estado interno modificable — solo el ViewModel puede cambiarlo
    private val _albumViewState: MutableStateFlow<AlbumViewState> =
        MutableStateFlow(AlbumViewState())

    // Estado de solo lectura que las pantallas pueden observar
    val albumViewState: StateFlow<AlbumViewState> get() = _albumViewState

    // Agrega una nueva falla a la lista
    fun registrarFalla(ubicacion: String, numeroInventario: String, descripcion: String, fotoUri: Uri?) {
        _albumViewState.update { state ->
            val nuevaFalla = Falla(
                id = state.fallas.size + 1, // El ID es el siguiente número en la lista
                ubicacion = ubicacion,
                numeroInventario = numeroInventario,
                descripcion = descripcion,
                fotoUri = fotoUri
            )
            state.copy(fallas = state.fallas + nuevaFalla) // Crea copia del estado con la nueva falla
        }
    }

    // Guarda temporalmente la URI de la foto tomada con la cámara
    fun setTempUri(uri: Uri?) {
        _albumViewState.update { it.copy(tempFileUri = uri) }
    }
}