package com.example.ejemplonav.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// Pantalla de ejemplo que muestra funciones extras del dispositivo
@Composable
fun VistaOtro(navigateToHome: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "OTRA SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToHome() }) {
            Text(text = "Navegar a la home")
        }
        abrirNavegador()  // Botón para abrir el navegador
        abrirEnvio()      // Botón para compartir texto
        AbrirGal()        // Botón para abrir la galería
    }
}

// Abre el navegador con una URL específica usando un Intent
@Composable
fun abrirNavegador(){
    val context = LocalContext.current
    Button(onClick = {
        // Intent.ACTION_VIEW abre la URL en el navegador predeterminado
        val inten = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/RodrigoSBA10/scammers"))
        context.startActivity(inten)
    }) {
        Text(text = "Abri GitHub")
    }
}

// Abre el menú de compartir para enviar texto a otras apps
@Composable
fun abrirEnvio(){
    val context = LocalContext.current
    Button(onClick = {
        val inten = Intent().apply {
            action = Intent.ACTION_SEND               // Acción de compartir
            putExtra(Intent.EXTRA_TEXT, "Este es el texto que quiero enviar") // Texto a compartir
            type = "text/plain"                       // Tipo de contenido
        }
        val sharedIntent = Intent.createChooser(inten, null) // Muestra el menú de apps disponibles
        context.startActivity(sharedIntent)
    }) {
        Text(text = "Enviar texto")
    }
}

// Abre la galería para seleccionar una imagen y la muestra en pantalla
@Composable
fun AbrirGal(){
    // Guarda la URI de la imagen seleccionada — null si no hay ninguna
    var imagenSelec by remember{ mutableStateOf<Uri?>(null)}

    // Lanzador que abre el selector de imágenes del sistema
    val photoPickerLauncher= rememberLauncherForActivityResult(
        contract=ActivityResultContracts.PickVisualMedia(),
        onResult={ uri -> imagenSelec=uri }  // Guarda la URI de la imagen elegida
    )

    Button(onClick = {
        // Lanza el selector filtrando solo imágenes
        photoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }) {
        Text(text = "Abrir galeria")
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imagenSelec != null) {
            Text(text = "Imagen seleccionada: ${imagenSelec?.path}") // Muestra la ruta de la imagen
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(  // Carga y muestra la imagen de forma eficiente
                model = imagenSelec,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        } else {
            Text(text = "No hay imagen seleccionada")
        }
    }
}