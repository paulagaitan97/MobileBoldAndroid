package com.gaitan.dev.clima_presentacion.comportamiento

import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase

data class LocalizadorEstado(
    val textoBusqueda: String = "",
    val claveSecreta: String = "",
    val cargandoBusqueda: Boolean = false,
    val detalleBusqueda: List<LocalizadorBase> = emptyList()
)
