package com.gaitan.dev.base_ui.componente.atomo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ImagenEscalable(
    imagenRecurso: Int,
    descripcionImagen: String,
    alineacion: Alignment = Alignment.Center,
    escalaAnimacion: Float
){
    Box(
        contentAlignment = alineacion,
        modifier = Modifier.fillMaxSize().background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = imagenRecurso),
            contentDescription = descripcionImagen,
            modifier = Modifier.padding(top = 40.dp).scale(escalaAnimacion)
        )
    }
}