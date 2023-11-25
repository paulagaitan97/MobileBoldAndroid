package com.gaitan.dev.clima_presentacion.comportamiento

sealed class LocalizadorEvento {
    data class cambioConsulta(val valorBusqueda: String, val clave: String): LocalizadorEvento()
    object realizarBusqueda: LocalizadorEvento()
}
