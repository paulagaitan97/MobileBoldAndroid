package com.gaitan.dev.clima_dominio.casouso

import com.gaitan.dev.clima_dominio.modelo.DetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador

class ObtenerDetalleUbicacionSeleccionada(
    private val iLocalizador: ILocalizador
) {
    suspend operator fun invoke(texto: String,  clave: String, filtroDia: Int): Result<DetalleUbicacion> {
        return iLocalizador.detalleUbicacionSeleccionada(apiKey = clave, query = texto, days = filtroDia)
    }
}