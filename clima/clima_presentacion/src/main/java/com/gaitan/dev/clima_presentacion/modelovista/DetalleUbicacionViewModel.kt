package com.gaitan.dev.clima_presentacion.modelovista

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaitan.dev.clima_dominio.casouso.ClimaCasosUsos
import com.gaitan.dev.clima_presentacion.comportamiento.DetalleUbicacionEstado
import com.gaitan.dev.clima_presentacion.comportamiento.DetalleUbicacionEvento
import com.gaitan.dev.clima_presentacion.comportamiento.LocalizadorEstado
import com.gaitan.dev.clima_presentacion.comportamiento.LocalizadorEvento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleUbicacionViewModel @Inject constructor(
    private val climaCasosUsos: ClimaCasosUsos
): ViewModel() {
    var detalleUbicacionEstado by mutableStateOf(DetalleUbicacionEstado())
        private set

    fun eventoGrafico(evento: DetalleUbicacionEvento) {
        when(evento) {
            is DetalleUbicacionEvento.verDetalleUbicacion -> {
                realizarBusquedaPorValor(textoBusqueda = evento.valorBusqueda, clave = evento.clave, filtroDias = evento.filtro)
            }
        }
    }


    fun realizarBusquedaPorValor(textoBusqueda: String, clave: String, filtroDias: Int) {
        viewModelScope.launch {
            detalleUbicacionEstado = detalleUbicacionEstado.copy(
                cargandoBusqueda = true
            )
            climaCasosUsos.detalleUbicacionSeleccionada(texto = textoBusqueda, clave = clave, filtroDia = filtroDias )
                .onSuccess { ubicacionDetalle ->
                    detalleUbicacionEstado = detalleUbicacionEstado.copy(
                        cargandoBusqueda = false
                    )
                }.onFailure {
                    detalleUbicacionEstado = detalleUbicacionEstado.copy(
                        cargandoBusqueda = true
                    )
                }
        }
    }
}