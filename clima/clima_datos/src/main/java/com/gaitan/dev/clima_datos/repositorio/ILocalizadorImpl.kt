package com.gaitan.dev.clima_datos.repositorio

import com.gaitan.dev.clima_datos.fuente.remoto.RutaClimaModulo
import com.gaitan.dev.clima_datos.fuente.remoto.mapeador.mapDetalleUbicacionDtoToDetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.DetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador
import io.sentry.Sentry

class ILocalizadorImpl(private val api: RutaClimaModulo) : ILocalizador {

    override suspend fun busquedaUbicaciones(apiKey: String, query: String): Result<List<LocalizadorBase>> {
        return try {
            val localizadorBaseDto = api.obtenerUbicacionPorBusqueda(apiKey = apiKey, query = query)
            Result.success(
                localizadorBaseDto.map { LocalizadorBase(ciudad = it.name, pais = it.country, region = it.region) }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun detalleUbicacionSeleccionada(apiKey: String, query: String, days: Int): Result<DetalleUbicacion> {
        return try {
            val detalleUbicacionDto = api.obtenerDetalleUbicacion(apiKey = apiKey, query = query, days = days)
            Result.success(
                mapDetalleUbicacionDtoToDetalleUbicacion(detalleUbicacionDto)
            )
        } catch (e: Exception) {
            Sentry.captureMessage("Error ubicacion seleccionada ${e.localizedMessage}")
            Result.failure(e)
        }
    }
}