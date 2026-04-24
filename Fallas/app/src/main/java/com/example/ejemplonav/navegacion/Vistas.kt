package com.example.ejemplonav.navegacion

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Home

@Serializable
data class RegistrarFalla(val fallaId: Int = -1)

@Serializable
data class DetalleFalla(val fallaId: Int)
