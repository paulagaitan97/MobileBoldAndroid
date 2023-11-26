package com.gaitan.dev.clima_dominio.modelo

data class DetalleUbicacion(
    val nombre: String,
    val region: String,
    val pais: String,
    val detalleUbicacionActual: DetalleActual,
    val pronostico:PronosticoUbicacion
)
