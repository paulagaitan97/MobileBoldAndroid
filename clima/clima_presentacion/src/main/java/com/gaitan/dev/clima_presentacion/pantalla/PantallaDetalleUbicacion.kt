package com.gaitan.dev.clima_presentacion.pantalla

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaitan.dev.clima_presentacion.comportamiento.DetalleUbicacionEvento
import com.gaitan.dev.clima_presentacion.modelovista.DetalleUbicacionViewModel

@Composable
fun PantallaDetalleUbicacion(
    detalleUbicacionViewModel: DetalleUbicacionViewModel = hiltViewModel(),
    valorSecreto: String,
    ciudad: String,
    dias: Int
){
    LaunchedEffect(key1 = true){
        detalleUbicacionViewModel.eventoGrafico(DetalleUbicacionEvento.verDetalleUbicacion(valorBusqueda = ciudad, clave = valorSecreto, filtro = dias))
    }
}