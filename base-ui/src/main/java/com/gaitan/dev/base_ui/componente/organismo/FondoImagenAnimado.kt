package com.gaitan.dev.base_ui.componente.organismo

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.gaitan.dev.base_ui.componente.molecula.FondoImagenPersonalizado

@Composable
fun FondoImagenAnimado(
    @DrawableRes imagenFondo: Int,
    @DrawableRes imagenLogo: Int,
    contenidoEscalaFondo: ContentScale,
    fondoColorDefecto: Color? = null,
    alineacionImagen: Alignment,
    valorObjetivoAnimarImagen: Float,
    duracionAnimacionimagen: Int
){
    val escala = remember { Animatable(0f) }

    FondoImagenPersonalizado(
        imagenFondo = imagenFondo,
        imagenLogo = imagenLogo,
        contenidoEscalaFondo = contenidoEscalaFondo,
        fondoColorDefecto = fondoColorDefecto,
        alineacionImagen = alineacionImagen,
        escalaImagen = escala.value
    )

    LaunchedEffect(true) {
        escala.animateTo(
            targetValue = valorObjetivoAnimarImagen,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = duracionAnimacionimagen, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }
}