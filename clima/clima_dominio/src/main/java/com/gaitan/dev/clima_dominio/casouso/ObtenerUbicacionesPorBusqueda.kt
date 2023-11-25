package com.gaitan.dev.clima_dominio.casouso

import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador

class ObtenerUbicacionesPorBusqueda(
    private val iLocalizador: ILocalizador
) {
    suspend operator fun invoke(texto: String,  clave: String): Result<List<LocalizadorBase>> {
        if (texto.isBlank()){
            return Result.success(emptyList())
        }
        return iLocalizador.busquedaUbicaciones(apiKey = clave, query = texto)
    }
}