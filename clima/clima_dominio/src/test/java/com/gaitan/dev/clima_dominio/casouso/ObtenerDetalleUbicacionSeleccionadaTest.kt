package com.gaitan.dev.clima_dominio.casouso

import com.gaitan.dev.clima_dominio.modelo.CondicionHoraDetalle
import com.gaitan.dev.clima_dominio.modelo.DetalleActual
import com.gaitan.dev.clima_dominio.modelo.DetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.gaitan.dev.clima_dominio.modelo.PronosticoUbicacion
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObtenerDetalleUbicacionSeleccionadaTest {

    @Mock
    lateinit var iLocalizador: ILocalizador

    @InjectMocks
    lateinit var obtenerDetalleUbicacionSeleccionada: ObtenerDetalleUbicacionSeleccionada

    @Test
    fun `ejecutar caso de uso - retorno exitoso`() = runBlocking {
        val texto = "Mede"
        val clave = "claveSecreta"
        val filtroDia = 7
        val detalleUbicacion = DetalleUbicacion(
            localizadorBase = LocalizadorBase(ciudad = "Medellin", pais = "Colombia", region = "Antioquia"),
            detalleUbicacionActual = DetalleActual(temperaturaC = 22.2, condicionHoraDetalle = CondicionHoraDetalle(icono = "//cdn.miicono")),
            pronostico = PronosticoUbicacion(pronosticoPosterior = listOf())
        )
        `when`(iLocalizador.detalleUbicacionSeleccionada(apiKey = clave, query = texto, days = filtroDia)).thenReturn(Result.success(detalleUbicacion))
        val result = obtenerDetalleUbicacionSeleccionada.invoke(texto, clave, filtroDia)
        assertThat(result.isSuccess).isTrue()
    }
}