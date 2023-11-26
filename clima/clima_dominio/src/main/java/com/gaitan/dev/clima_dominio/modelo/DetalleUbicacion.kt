package com.gaitan.dev.clima_dominio.modelo

data class DetalleUbicacion(
    val localizadorBase: LocalizadorBase,
    val detalleUbicacionActual: DetalleActual,
    val pronostico:PronosticoUbicacion
)
