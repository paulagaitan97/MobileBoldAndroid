package com.gaitan.dev.clima_dominio.repositorio

import com.gaitan.dev.clima_dominio.modelo.DetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase

interface ILocalizador {
    suspend fun busquedaUbicaciones(apiKey: String, query: String): Result<List<LocalizadorBase>>
    suspend fun detalleUbicacionSeleccionada(apiKey: String, query: String, days:Int): Result<DetalleUbicacion>
}