package com.gaitan.dev.base_ui.componente.atomo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun FondoImagen(
    modifier: Modifier = Modifier,
    imagenFondo: Int,
    descripcionImagen: String,
    escalaImagen: ContentScale
) {
    Box(modifier = modifier.fillMaxSize().background(color = Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = imagenFondo),
            contentDescription = descripcionImagen,
            contentScale = escalaImagen,
            modifier = Modifier.fillMaxSize()
        )
    }
}