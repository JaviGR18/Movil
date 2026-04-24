package com.example.ejemplonav.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejemplonav.ui.model.AlbumVIewModel
import com.example.ejemplonav.ui.model.AlbumViewState

// Pantalla de ejemplo base para el manejo de la cámara
// Esta pantalla fue el punto de partida antes de implementar VistaRegistrarFalla
@Composable
fun VistaCamara(modifier: Modifier = Modifier, viewModel: AlbumVIewModel){

    // Observa el estado del ViewModel para reaccionar a cambios
    val viewState: AlbumViewState by viewModel.albumViewState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp)
            .verticalScroll(rememberScrollState()) // Permite scroll si el contenido es largo
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            // Botón base para tomar foto — la lógica se implementó en VistaRegistrarFalla
            Button(onClick = {

            }) {
                Text(text = "Tomar foto")
            }
        }
    }
}