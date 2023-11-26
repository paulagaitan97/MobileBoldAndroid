package com.gaitan.dev.clima_dominio.di

import com.gaitan.dev.clima_dominio.casouso.ClimaCasosUsos
import com.gaitan.dev.clima_dominio.casouso.ObtenerDetalleUbicacionSeleccionada
import com.gaitan.dev.clima_dominio.casouso.ObtenerUbicacionesPorBusqueda
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ModuloClimaDominio {
    @ViewModelScoped
    @Provides
    fun proveedorCasoDeUsoClima(
        iLocalizador: ILocalizador
    ): ClimaCasosUsos {
        return ClimaCasosUsos(
            obtenerUbicacionesPorBusqueda = ObtenerUbicacionesPorBusqueda(iLocalizador = iLocalizador),
            detalleUbicacionSeleccionada = ObtenerDetalleUbicacionSeleccionada(iLocalizador = iLocalizador)
        )
    }
}