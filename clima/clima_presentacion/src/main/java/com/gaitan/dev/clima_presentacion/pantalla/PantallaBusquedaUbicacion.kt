package com.gaitan.dev.clima_presentacion.pantalla

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaitan.dev.base_ui.componente.molecula.BarraDeBusqueda
import com.gaitan.dev.base_ui.componente.molecula.TarjetaBusquedaDetalle
import com.gaitan.dev.base_ui.componente.molecula.TarjetaInformativaVertical
import com.gaitan.dev.clima_presentacion.comportamiento.LocalizadorEvento
import com.gaitan.dev.clima_presentacion.modelovista.LocalizadorUbicacionViewModel

@Composable
fun PantallaBusquedaUbicacion(
    localizadorUbicacionViewModel: LocalizadorUbicacionViewModel = hiltViewModel(),
    valorSecreto: String,
    eventoDetalleUbicacion: (String, String, Int) -> Unit
){
    var estadoLocalizador = localizadorUbicacionViewModel.localizadorEstado
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (barraDeBusqueda, listaDetalles) = createRefs()

        BarraDeBusqueda(
            texto = estadoLocalizador.textoBusqueda,
            textoActual = {
                localizadorUbicacionViewModel.eventoGrafico(LocalizadorEvento.cambioConsulta(valorBusqueda = it, clave = valorSecreto))
                localizadorUbicacionViewModel.eventoGrafico(LocalizadorEvento.realizarBusqueda)
            },
            eventoBusqueda = {
                localizadorUbicacionViewModel.eventoGrafico(LocalizadorEvento.realizarBusqueda)
            },
            modifier = Modifier.constrainAs(barraDeBusqueda) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        LazyColumn(modifier = Modifier
            .padding(8.dp)
            .constrainAs(listaDetalles) {
                top.linkTo(barraDeBusqueda.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }) {
            items(estadoLocalizador.detalleBusqueda.size) { indice ->
                TarjetaBusquedaDetalle(
                    titulo = estadoLocalizador.detalleBusqueda[indice].ciudad,
                    descripcion = estadoLocalizador.detalleBusqueda[indice].pais,
                    onClick = {
                       eventoDetalleUbicacion(estadoLocalizador.detalleBusqueda[indice].ciudad, valorSecreto, 3)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            estadoLocalizador.cargandoBusqueda -> CircularProgressIndicator()
            estadoLocalizador.detalleBusqueda.isEmpty() -> {
                TarjetaInformativaVertical(
                    representacion = Icons.Default.Delete,
                    texto = "Sin coincidencias"
                )
            }
        }
    }
}