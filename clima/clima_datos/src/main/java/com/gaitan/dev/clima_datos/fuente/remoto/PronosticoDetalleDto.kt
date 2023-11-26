package com.gaitan.dev.clima_datos.fuente.remoto

data class PronosticoDetalleDto(
    val date: String,
    val day: PronosticoDiaDto,
    val hour: List<PronosticoHoraDetalleDto>
)
