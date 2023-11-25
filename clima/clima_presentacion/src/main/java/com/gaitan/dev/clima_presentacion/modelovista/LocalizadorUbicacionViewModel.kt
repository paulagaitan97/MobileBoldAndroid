package com.gaitan.dev.clima_presentacion.modelovista

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaitan.dev.clima_dominio.casouso.ClimaCasosUsos
import com.gaitan.dev.clima_presentacion.comportamiento.LocalizadorEstado
import com.gaitan.dev.clima_presentacion.comportamiento.LocalizadorEvento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalizadorUbicacionViewModel @Inject constructor(
    private val climaCasosUsos: ClimaCasosUsos
): ViewModel() {
    var localizadorEstado by mutableStateOf(LocalizadorEstado())
        private set

    fun eventoGrafico(evento: LocalizadorEvento) {
        when(evento) {
            is LocalizadorEvento.realizarBusqueda -> {
                realizarBusquedaPorValor()
            }
            is LocalizadorEvento.cambioConsulta -> {
                localizadorEstado = localizadorEstado.copy(textoBusqueda = evento.valorBusqueda, claveSecreta = evento.clave)
            }
        }
    }


    fun realizarBusquedaPorValor() {
        viewModelScope.launch {
            localizadorEstado = localizadorEstado.copy(
                cargandoBusqueda = true,
                detalleBusqueda = emptyList()
            )
            climaCasosUsos.obtenerUbicacionesPorBusqueda(texto = localizadorEstado.textoBusqueda, clave = localizadorEstado.claveSecreta)
                .onSuccess { ubicacionesDisponibles ->
                    localizadorEstado = localizadorEstado.copy(
                        detalleBusqueda = ubicacionesDisponibles, cargandoBusqueda = false
                    )
                }.onFailure {
                    localizadorEstado = localizadorEstado.copy(
                        cargandoBusqueda = false
                    )
                }
        }
    }
}