package com.gaitan.dev.mobileboldandroid.navegacion

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gaitan.dev.clima_presentacion.pantalla.PantallaBusquedaUbicacion
import com.gaitan.dev.clima_presentacion.pantalla.PantallaSplashAnimado
import com.gaitan.dev.mobileboldandroid.BuildConfig
import com.gaitan.dev.mobileboldandroid.R

fun NavGraphBuilder.climaGrafo(
    navegacionBase: () -> Unit
){
    navigation(
        route = TipoGrafoNavegacion.CLIMA,
        startDestination = PantallaNavegacion.ListaUbicaciones.ruta
    ) {
        composable(PantallaNavegacion.ListaUbicaciones.ruta){
            PantallaBusquedaUbicacion(valorSecreto = BuildConfig.API_KEY)
        }
    }
}