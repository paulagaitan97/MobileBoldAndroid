package com.gaitan.dev.mobileboldandroid.navegacion

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gaitan.dev.clima_presentacion.pantalla.PantallaSplashAnimado
import com.gaitan.dev.mobileboldandroid.R

fun NavGraphBuilder.introduccionGrafo(
    navegacionBase: () -> Unit
){
    navigation(
        route = TipoGrafoNavegacion.INTRODUCCION,
        startDestination = PantallaNavegacion.Splash.ruta
    ) {
        composable(PantallaNavegacion.Splash.ruta){
            PantallaSplashAnimado(
                imagenFondo = R.drawable.icono_bold_compartido,
                contenidoEscalaFondo = ContentScale.FillBounds,
                fondoColorDefecto = Color.White,
                imagenLogo = R.drawable.icono_bold_compartido,
                alineacionImagen = Alignment.Center,
                valorObjetivoAnimarImagen = 0.4f,
                duracionAnimacionimagen = 1200,
                navegacionPrincipal = navegacionBase
            )
        }
    }
}