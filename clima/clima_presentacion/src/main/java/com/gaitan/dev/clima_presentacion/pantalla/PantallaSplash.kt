package com.gaitan.dev.clima_presentacion.pantalla

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.gaitan.dev.base_ui.componente.organismo.FondoImagenAnimado
import kotlinx.coroutines.delay

@Composable
fun PantallaSplashAnimado(
    @DrawableRes imagenFondo: Int,
    contenidoEscalaFondo: ContentScale,
    fondoColorDefecto: Color,
    @DrawableRes imagenLogo: Int,
    alineacionImagen: Alignment,
    valorObjetivoAnimarImagen: Float,
    duracionAnimacionimagen: Int,
    navegacionPrincipal: () -> Unit
){
    FondoImagenAnimado(
        imagenFondo = imagenFondo,
        imagenLogo = imagenLogo,
        contenidoEscalaFondo = contenidoEscalaFondo,
        fondoColorDefecto = fondoColorDefecto,
        alineacionImagen = alineacionImagen,
        valorObjetivoAnimarImagen = valorObjetivoAnimarImagen,
        duracionAnimacionimagen = duracionAnimacionimagen
    )
    
    LaunchedEffect(key1 = true){
        delay(5000)
        navegacionPrincipal()
    }
}