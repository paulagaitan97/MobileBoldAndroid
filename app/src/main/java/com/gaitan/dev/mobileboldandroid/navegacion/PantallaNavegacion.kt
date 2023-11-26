package com.gaitan.dev.mobileboldandroid.navegacion

sealed class PantallaNavegacion(val ruta: String) {
    object Splash: PantallaNavegacion(RutaNavegacion.feat_introduccion_splash)
    object ListaUbicaciones: PantallaNavegacion(RutaNavegacion.feat_listado_ubicaciones)

    object DetalleUbicacion: PantallaNavegacion(RutaNavegacion.feat_detalle_ubicacion+ "?ciudad={ciudad}?clave={clave}?dias={dias}") {
        fun crearRuta(ciudad: String, clave: String, dias: Int) =
            RutaNavegacion.feat_detalle_ubicacion + "?ciudad=$ciudad?clave=$clave?dias=$dias"
    }
}