package com.gaitan.dev.base_ui.componente.molecula

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Dialogo(mensaje: String, titulo:String,  textoBoton: String, eventoSalir: () -> Unit) {
    AlertDialog(
        onDismissRequest = { eventoSalir() },
        title = { Text(titulo) },
        text = { Text(mensaje) },
        confirmButton = {
            Button(
                onClick = { eventoSalir() },
            ) {
                Text(textoBoton)
            }
        }
    )
}