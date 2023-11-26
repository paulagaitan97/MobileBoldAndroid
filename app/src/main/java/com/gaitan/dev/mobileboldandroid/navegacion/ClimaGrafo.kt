package com.gaitan.dev.mobileboldandroid.navegacion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.gaitan.dev.clima_presentacion.pantalla.PantallaBusquedaUbicacion
import com.gaitan.dev.clima_presentacion.pantalla.PantallaDetalleUbicacion
import com.gaitan.dev.mobileboldandroid.BuildConfig

fun NavGraphBuilder.climaGrafo(
    navegacionBase: () -> Unit,
    navControlador: NavHostController
){
    navigation(
        route = TipoGrafoNavegacion.CLIMA,
        startDestination = PantallaNavegacion.ListaUbicaciones.ruta
    ) {
        composable(PantallaNavegacion.ListaUbicaciones.ruta){
            PantallaBusquedaUbicacion(valorSecreto = BuildConfig.API_KEY, eventoDetalleUbicacion = { ciudad, clave, dias ->
                val ruta = PantallaNavegacion.DetalleUbicacion.crearRuta(ciudad = ciudad, clave = clave, dias = dias)
                navControlador.navigate(route = ruta)
            })
        }
        composable(PantallaNavegacion.DetalleUbicacion.ruta,
            arguments = listOf(
                navArgument("ciudad") { defaultValue = "" },
                navArgument("clave") { defaultValue = "" },
                navArgument("dias") { defaultValue = 0 }
            )){
            val ciudad = it.arguments?.getString("ciudad")
            val clave = it.arguments?.getString("clave")
            val dias = it.arguments?.getInt("dias")
            PantallaDetalleUbicacion(ciudad = ciudad!!, valorSecreto = clave!!, dias = dias!!, eventoVolver = {
                navControlador.popBackStack()
            })
        }
    }
}