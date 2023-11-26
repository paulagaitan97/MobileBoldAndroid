package com.gaitan.dev.clima_datos.fuente.remoto

data class DetalleActualDto(
    val temp_c: Double,
    val condition: CondicionHoraDetalleDto
)
