package com.example.ejemplonav.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

// Pantalla de detalle del proyecto de ejemplo original
// Recibe un nombre y muestra dos opciones de navegación
@Composable
fun VistaDetalles(
    name: String,                       // Nombre recibido desde la pantalla anterior
    navigateToSettings:() -> Unit,      // Acción para navegar a ajustes
    navigateBack: () -> Unit            // Acción para regresar al Login
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))

        // Muestra el nombre que se pasó como parámetro desde VistaHome
        Text(text = "DETAIL SCREEN $name", fontSize = 25.sp)

        Spacer(modifier = Modifier.weight(1f))

        // Navega a la pantalla de ajustes
        Button(onClick = { navigateToSettings() }) {
            Text(text = "Navegar a ajustes")
        }

        // Regresa al Login limpiando toda la pila de navegación
        Button(onClick = { navigateBack() }) {
            Text(text = "Logout")
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}