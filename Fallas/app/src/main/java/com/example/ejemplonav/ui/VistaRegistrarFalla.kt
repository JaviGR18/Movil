package com.example.ejemplonav.ui

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.ejemplonav.ui.model.AlbumVIewModel
import java.io.File

@Composable
fun VistaRegistrarFalla(
    viewModel: AlbumVIewModel,
    onFallaRegistrada: () -> Unit
) {
    val context = LocalContext.current
    val viewState by viewModel.albumViewState.collectAsState()

    var ubicacion by remember { mutableStateOf("") }
    var numeroInventario by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fotoUri by remember { mutableStateOf<Uri?>(null) }

    // Lanzador para tomar foto con la camara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { exito ->
        if (exito) {
            fotoUri = viewState.tempFileUri
        }
    }

    // Lanzador para seleccionar foto de galeria
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) fotoUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "REGISTRAR FALLA", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicación del equipo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = numeroInventario,
            onValueChange = { numeroInventario = it },
            label = { Text("Número de inventario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción de la falla") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar foto si ya hay una seleccionada
        if (fotoUri != null) {
            AsyncImage(
                model = fotoUri,
                contentDescription = "Foto de la falla",
                modifier = Modifier.size(200.dp).padding(8.dp)
            )
        } else {
            Text(text = "Sin foto", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Boton para tomar foto con camara
        Button(onClick = {
            val uri = crearArchivoFoto(context)
            viewModel.setTempUri(uri)
            cameraLauncher.launch(uri)
        }) {
            Text(text = "Tomar foto")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Boton para seleccionar de galeria
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text(text = "Seleccionar de galería")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Boton para guardar la falla
        Button(
            onClick = {
                if (ubicacion.isNotBlank() && numeroInventario.isNotBlank() && descripcion.isNotBlank()) {
                    viewModel.registrarFalla(ubicacion, numeroInventario, descripcion, fotoUri)
                    onFallaRegistrada()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Guardar falla")
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

// Crea un archivo temporal para guardar la foto de la camara
fun crearArchivoFoto(context: Context): Uri {
    val archivo = File(context.filesDir, "foto_falla_${System.currentTimeMillis()}.jpg")
    return FileProvider.getUriForFile(context, "${context.packageName}.provider", archivo)
}
