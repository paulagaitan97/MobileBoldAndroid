package com.gaitan.dev.clima_datos.repositorio

import com.gaitan.dev.clima_datos.fuente.remoto.CondicionHoraDetalleDto
import com.gaitan.dev.clima_datos.fuente.remoto.DetalleActualDto
import com.gaitan.dev.clima_datos.fuente.remoto.DetalleUbicacionDto
import com.gaitan.dev.clima_datos.fuente.remoto.LocalizadorBaseDto
import com.gaitan.dev.clima_datos.fuente.remoto.PronosticoUbicacionDto
import com.gaitan.dev.clima_datos.fuente.remoto.RutaClimaModulo
import com.gaitan.dev.clima_datos.fuente.remoto.mapeador.mapDetalleUbicacionDtoToDetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ILocalizadorImplTest {
    @Mock
    lateinit var api: RutaClimaModulo

    @InjectMocks
    lateinit var localizadorImpl: ILocalizadorImpl

    @Test
    fun `busquedaUbicaciones - éxito`() = runBlocking {
        val apiKey = "valorSecreto"
        val query = "mede"
        val localizadorBaseDtoList = listOf(LocalizadorBaseDto("Medellin", "Colombia", "Antioquia"))
        `when`(api.obtenerUbicacionPorBusqueda(apiKey, query)).thenReturn(localizadorBaseDtoList)
        val result = localizadorImpl.busquedaUbicaciones(apiKey, query)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(localizadorBaseDtoList.map {
            LocalizadorBase(ciudad = it.name, pais = it.country, region = it.region)
        })
    }

    @Test
    fun `busquedaUbicaciones - error`() = runBlocking {
        val apiKey = "valorSecreto"
        val query = "mede"
        doAnswer {
            throw IOException("Error de red")
        }.`when`(api).obtenerUbicacionPorBusqueda(apiKey, query)

        val result = localizadorImpl.busquedaUbicaciones(apiKey, query)
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(IOException::class.java)
    }

    @Test
    fun `detalleUbicacionSeleccionada - éxito`() = runBlocking {
        val apiKey = "valorSecreto"
        val query = "mede"
        val days = 7
        val detalleUbicacionDto = DetalleUbicacionDto(
            location = LocalizadorBaseDto(name = "Medellin", country = "Colombia", region = "Antioquia"),
            current = DetalleActualDto(temp_c = 22.2, condition = CondicionHoraDetalleDto(icon = "//cdn.miicono")),
            forecast = PronosticoUbicacionDto(forecastday = listOf())
        )
        `when`(api.obtenerDetalleUbicacion(apiKey, query, days)).thenReturn(detalleUbicacionDto)

        val result = localizadorImpl.detalleUbicacionSeleccionada(apiKey, query, days)
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isEqualTo(mapDetalleUbicacionDtoToDetalleUbicacion(detalleUbicacionDto))
    }

    @Test
    fun `detalleUbicacionSeleccionada - error`() = runBlocking {
        val apiKey = "valorSecreto"
        val query = "mede"
        val days = 7
        doAnswer {
            throw IOException("Error de red")
        }.`when`(api).obtenerDetalleUbicacion(apiKey, query, days)

        val result = localizadorImpl.detalleUbicacionSeleccionada(apiKey, query, days)
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(IOException::class.java)
    }

}