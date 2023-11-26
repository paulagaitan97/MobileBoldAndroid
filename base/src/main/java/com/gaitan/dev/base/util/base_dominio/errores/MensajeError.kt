package com.gaitan.dev.base.util.base_dominio.errores

sealed class MensajeError(val mensaje: String) {
    object conexionDeServidor : MensajeError("Tenemos problemas en nuestro servidores")
    object estructuraNoValida : MensajeError("Estructura no valida")
    object errorGeneral : MensajeError("Tenemos problemas en nuestro servidores, intenta de nuevo")

    companion object{
        fun clasificacionError(codigo: Int): MensajeError {
            return when (codigo){
                    500 -> conexionDeServidor
                    404 -> estructuraNoValida
                    307 -> conexionDeServidor
                    else -> errorGeneral
            }
        }
    }
}