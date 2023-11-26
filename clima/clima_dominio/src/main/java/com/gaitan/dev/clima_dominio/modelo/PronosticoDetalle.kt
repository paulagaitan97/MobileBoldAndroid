package com.gaitan.dev.clima_dominio.modelo

data class PronosticoDetalle(
    val fecha: String,
    val horaDia: List<PronosticoHoraDetalle>
)
