package com.gaitan.dev.clima_datos.fuente.remoto


data class DetalleUbicacionDto(
    val location: LocalizadorBaseDto,
    val current: DetalleActualDto,
    val forecast: PronosticoUbicacionDto
)
