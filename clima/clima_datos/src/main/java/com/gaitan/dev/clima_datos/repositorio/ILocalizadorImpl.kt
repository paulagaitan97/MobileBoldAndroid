package com.gaitan.dev.clima_datos.repositorio

import android.util.Log
import com.gaitan.dev.clima_datos.fuente.remoto.RutaClimaModulo
import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador

class ILocalizadorImpl(private val api: RutaClimaModulo) : ILocalizador {

    override suspend fun busquedaUbicaciones(apiKey: String, query: String): Result<List<LocalizadorBase>> {
        return try {
            val localizadorBaseDto = api.obtenerUbicacionPorBusqueda(apiKey = apiKey, query = query)
            Result.success(
                localizadorBaseDto.map { LocalizadorBase(ciudad = it.name, pais = it.country) }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}