package com.gaitan.dev.base_ui.componente.molecula

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.gaitan.dev.base_ui.componente.atomo.FondoImagen
import com.gaitan.dev.base_ui.componente.atomo.ImagenEscalable

@Composable
fun FondoImagenPersonalizado(
    @DrawableRes imagenFondo: Int,
    @DrawableRes imagenLogo: Int,
    contenidoEscalaFondo: ContentScale,
    fondoColorDefecto: Color? = null,
    alineacionImagen: Alignment,
    escalaImagen: Float
) {
    if (fondoColorDefecto != null) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(fondoColorDefecto)) {
            ImagenEscalable(
                alineacion = alineacionImagen,
                imagenRecurso = imagenLogo,
                escalaAnimacion = escalaImagen,
                descripcionImagen = "Imagen detalle animado"
            )
        }

    } else {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            FondoImagen(
                imagenFondo = imagenFondo,
                escalaImagen = contenidoEscalaFondo,
                descripcionImagen = "Imagen detalle animado"
            )
            ImagenEscalable(
                alineacion = alineacionImagen,
                imagenRecurso = imagenLogo,
                escalaAnimacion = escalaImagen,
                descripcionImagen = "Imagen detalle animado"
            )
        }
    }
}