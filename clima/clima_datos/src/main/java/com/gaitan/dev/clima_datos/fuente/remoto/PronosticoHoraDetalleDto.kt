package com.gaitan.dev.clima_datos.fuente.remoto

data class PronosticoHoraDetalleDto(
    val time: String,
    val temp_c: Double,
    val condition: CondicionHoraDetalleDto
)
