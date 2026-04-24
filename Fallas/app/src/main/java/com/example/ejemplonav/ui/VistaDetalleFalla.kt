package com.example.ejemplonav.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ejemplonav.ui.model.AlbumVIewModel
import com.example.ejemplonav.ui.model.Falla


@Composable
fun VistaDetalleFalla(
    fallaId: Int,
    viewModel: AlbumVIewModel,
    onBack: () -> Unit
) {
    val viewState by viewModel.albumViewState.collectAsState()
    val falla = viewState.fallas.find { it.id == fallaId }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "DETALLE DE FALLA", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(16.dp))

        if (falla != null) {
            Text(text = "Falla #${falla.id}", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Ubicación: ${falla.ubicacion}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "No. Inventario: ${falla.numeroInventario}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Descripción:", fontSize = 16.sp)
            Text(text = falla.descripcion, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))

            if (falla.fotoUri != null) {
                Text(text = "Foto:", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = falla.fotoUri,
                    contentDescription = "Foto de la falla",
                    modifier = Modifier.size(250.dp)
                )
            } else {
                Text(text = "Sin foto registrada", fontSize = 14.sp)
            }
        } else {
            Text(text = "Falla no encontrada", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Regresar")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}