package com.example.ejemplonav.ui
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplonav.ui.model.AlbumVIewModel
import com.example.ejemplonav.ui.model.Falla


@Composable
fun VistaHome(
    viewModel: AlbumVIewModel,
    onRegistrarFalla: () -> Unit,
    onVerDetalle: (Int) -> Unit
) {
    val viewState by viewModel.albumViewState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "FALLAS REGISTRADAS", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRegistrarFalla) {
            Text(text = "Registrar nueva falla")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewState.fallas.isEmpty()) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "No hay fallas registradas", fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewState.fallas) { falla ->
                    TarjetaFalla(falla = falla, onClick = { onVerDetalle(falla.id) })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun TarjetaFalla(falla: Falla, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Falla #${falla.id}", fontSize = 16.sp)
                Text(text = "Inv: ${falla.numeroInventario}", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Ubicación: ${falla.ubicacion}", fontSize = 14.sp)
            Text(text = "Descripción: ${falla.descripcion}", fontSize = 14.sp)
        }
    }
}
