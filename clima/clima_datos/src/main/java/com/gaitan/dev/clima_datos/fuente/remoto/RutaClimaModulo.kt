package com.gaitan.dev.clima_datos.fuente.remoto

import retrofit2.http.GET
import retrofit2.http.Query

interface RutaClimaModulo {
    companion object {
        const val URL_PRINCIPAL = "http://api.weatherapi.com/v1/"
    }
    @GET("search.json")
    suspend fun obtenerUbicacionPorBusqueda(@Query("key") apiKey: String, @Query("q") query: String): List<LocalizadorBaseDto>
}