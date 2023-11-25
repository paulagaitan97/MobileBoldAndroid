package com.gaitan.dev.mobileboldandroid.navegacion

sealed class PantallaNavegacion(val ruta: String) {
    object Splash: PantallaNavegacion(RutaNavegacion.feat_introduccion_splash)
    object ListaUbicaciones: PantallaNavegacion(RutaNavegacion.feat_listado_ubicaciones)
}