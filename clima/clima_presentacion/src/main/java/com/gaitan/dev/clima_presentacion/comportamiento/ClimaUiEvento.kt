package com.gaitan.dev.clima_presentacion.comportamiento

sealed class ClimaUiEvento {

    data class MostrarModal(
        val descripcion : String = "",
        val mostrarModal : Boolean = false,
    ) : ClimaUiEvento()
}
