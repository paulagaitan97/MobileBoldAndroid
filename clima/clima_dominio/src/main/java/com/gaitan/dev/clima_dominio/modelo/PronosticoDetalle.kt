package com.gaitan.dev.clima_dominio.modelo

data class PronosticoDetalle(
    val fecha: String,
    val dia: PronosticoDia,
    val horaDia: List<PronosticoHoraDetalle>
)
