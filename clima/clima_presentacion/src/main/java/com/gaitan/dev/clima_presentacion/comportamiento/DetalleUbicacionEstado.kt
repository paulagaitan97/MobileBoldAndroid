package com.gaitan.dev.clima_presentacion.comportamiento

import com.gaitan.dev.clima_dominio.modelo.DetalleUbicacion

data class DetalleUbicacionEstado(
    val cargandoBusqueda: Boolean = false,
    val ubicacionDetalle: DetalleUbicacion? = null
)
