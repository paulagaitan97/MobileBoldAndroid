package com.gaitan.dev.clima_presentacion.comportamiento

sealed class DetalleUbicacionEvento {
    data class verDetalleUbicacion(val valorBusqueda: String, val clave: String, val filtro: Int): DetalleUbicacionEvento()
}
