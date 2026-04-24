package com.example.ejemplonav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ejemplonav.navegacion.NavigationWrapper
import com.example.ejemplonav.ui.theme.EjemploNAvTheme

// Punto de entrada de la app — es la primera clase que ejecuta Android
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que la app use toda la pantalla incluyendo la barra de estado
        enableEdgeToEdge()

        // Define el contenido visual de la app usando Jetpack Compose
        setContent {
            EjemploNAvTheme {  // Aplica el tema visual de la app (colores, tipografía)
                NavigationWrapper()  // Inicia el sistema de navegación entre pantallas
            }
        }
    }
}

// Función de ejemplo para mostrar un saludo — solo para pruebas
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// Vista previa en Android Studio — no aparece en la app real
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploNAvTheme {
        Greeting("Android")
    }
}