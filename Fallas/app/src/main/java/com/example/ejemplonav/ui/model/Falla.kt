package com.example.ejemplonav.ui.model

import android.net.Uri

// Representa una falla registrada en un equipo del laboratorio
// data class genera automáticamente métodos útiles como copy() y equals()
data class Falla(
    val id: Int,                    // Identificador único de la falla
    val ubicacion: String,          // Ubicación física del equipo en el laboratorio
    val numeroInventario: String,   // Número de inventario del equipo dañado
    val descripcion: String,        // Descripción de la falla
    val fotoUri: Uri? = null        // Foto opcional — null si no se tomó foto
)