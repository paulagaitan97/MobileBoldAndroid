package com.gaitan.dev.clima_presentacion.pantalla

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.gaitan.dev.clima_presentacion.comportamiento.DetalleUbicacionEvento
import com.gaitan.dev.clima_presentacion.modelovista.DetalleUbicacionViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.gaitan.dev.base_ui.componente.molecula.Dialogo
import com.gaitan.dev.base_ui.componente.organismo.DetalleTemperatura
import com.gaitan.dev.base_ui.componente.organismo.EncabezadoInformativo
import com.gaitan.dev.base_ui.componente.organismo.TarjetaTemperatura
import com.gaitan.dev.clima_dominio.modelo.PronosticoDetalle
import com.gaitan.dev.clima_presentacion.comportamiento.ClimaUiEvento
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PantallaDetalleUbicacion(
    detalleUbicacionViewModel: DetalleUbicacionViewModel = hiltViewModel(),
    valorSecreto: String,
    ciudad: String,
    dias: Int,
    eventoVolver: () -> Unit
) {
    val estadoDetalle = detalleUbicacionViewModel.detalleUbicacionEstado
    var esVisibleModal by remember { mutableStateOf(false) }
    var descripcion by remember { mutableStateOf("" ) }
    var indiceSeleccionado by rememberSaveable {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = true) {
        detalleUbicacionViewModel.eventoGrafico(
            DetalleUbicacionEvento.verDetalleUbicacion(
                valorBusqueda = ciudad,
                clave = valorSecreto,
                filtro = dias
            )
        )
        detalleUbicacionViewModel.uiEvento.collect { evento ->
            when(evento) {
                is ClimaUiEvento.MostrarModal -> {
                    esVisibleModal = evento.mostrarModal
                    descripcion = evento.descripcion
                }
            }
        }
    }

    if (estadoDetalle.ubicacionDetalle != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Detalles de Ubicación") },
                    navigationIcon = {
                        IconButton(onClick = { eventoVolver() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            }
        ) { relleno ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(relleno).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EncabezadoInformativo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textoCiudad = estadoDetalle.ubicacionDetalle!!.localizadorBase.ciudad,
                    textoRegion = estadoDetalle.ubicacionDetalle!!.localizadorBase.region,
                    textoPais = estadoDetalle.ubicacionDetalle!!.localizadorBase.pais,
                    valorTemperatura = estadoDetalle.ubicacionDetalle!!.detalleUbicacionActual.temperaturaC
                )
                Spacer(modifier = Modifier.height(16.dp))
                ListadoFechas(
                    fechas = estadoDetalle.ubicacionDetalle.pronostico.pronosticoPosterior,
                    onFechaSeleccionada = { indice ->
                        indiceSeleccionado = indice
                    },
                    fechaSeleccionada = indiceSeleccionado
                )
                Spacer(modifier = Modifier.height(16.dp))

                TarjetaTemperatura(titulo = "Temperatura promedio "+estadoDetalle.ubicacionDetalle.pronostico.pronosticoPosterior[indiceSeleccionado].dia.promedioTemperatura) {
                    LazyRow {
                        items(estadoDetalle.ubicacionDetalle.pronostico.pronosticoPosterior[indiceSeleccionado].horaDia) { temperatura ->
                            val hora = obtenerHoraEnFormato(temperatura.tiempo)
                            DetalleTemperatura(
                                hora = hora,
                                temperaturaHora = temperatura.temperaturaC.toString(),
                                representacion = temperatura.condicion.icono
                            )
                        }
                    }
                }
            }

        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            estadoDetalle.cargandoBusqueda -> CircularProgressIndicator()
        }
    }

    if (esVisibleModal){
        Dialogo(
            mensaje = descripcion, titulo = "Error", textoBoton = "Salir",
            eventoSalir = { esVisibleModal = false }
        )
    }
}

@Composable
fun ListadoFechas(fechas: List<PronosticoDetalle>, onFechaSeleccionada: (Int) -> Unit, fechaSeleccionada: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        fechas.forEachIndexed { index, pronostico ->
            FechaButton(fecha = pronostico.fecha, onFechaSeleccionada = { onFechaSeleccionada(index) }, posicionSeleccionada = index == fechaSeleccionada)
        }
    }
}

@Composable
fun FechaButton(fecha: String, onFechaSeleccionada: () -> Unit, posicionSeleccionada: Boolean) {
    val fechaActual = remember { LocalDate.parse(fecha) }
    val diaSemana = remember { fechaActual.dayOfWeek }
    val diaMes = remember { fechaActual.dayOfMonth }
    Column(
        modifier = Modifier
            .background(if (posicionSeleccionada) MaterialTheme.colors.secondary else MaterialTheme.colors.primary)
            .clickable { onFechaSeleccionada() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = diaMes.toString(), color = Color.White)
        Text(text = diaSemana.name, color = Color.White)
    }
}
fun obtenerHoraEnFormato(fechaYHora: String): String {
    val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val fechaYHoraActual = LocalDateTime.parse(fechaYHora, formato)
    return fechaYHoraActual.format(DateTimeFormatter.ofPattern("hh:mm a"))
}