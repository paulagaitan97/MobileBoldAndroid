package com.gaitan.dev.clima_dominio.modelo

data class PronosticoHoraDetalle(
    val tiempo: String,
    val temperaturaC: Double,
    val condicion: CondicionHoraDetalle
)
