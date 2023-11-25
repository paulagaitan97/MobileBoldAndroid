package com.gaitan.dev.mobileboldandroid.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun GrafoDeNavegacionApp(
    modifier: Modifier = Modifier,
    navControlador: NavHostController
){
    NavHost(
        modifier = modifier,
        navController = navControlador,
        route = TipoGrafoNavegacion.ROOT,
        startDestination = TipoGrafoNavegacion.INTRODUCCION
    ){
        introduccionGrafo(navegacionBase = {
            navControlador.navigate(TipoGrafoNavegacion.CLIMA)
        })

        climaGrafo(navegacionBase = {})
    }
}