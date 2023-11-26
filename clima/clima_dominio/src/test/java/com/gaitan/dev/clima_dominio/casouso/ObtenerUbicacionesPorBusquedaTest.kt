package com.gaitan.dev.clima_dominio.casouso


import com.gaitan.dev.clima_dominio.modelo.LocalizadorBase
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObtenerUbicacionesPorBusquedaTest {
    @Mock
    lateinit var iLocalizador: ILocalizador

    @InjectMocks
    lateinit var obtenerUbicacionesPorBusqueda: ObtenerUbicacionesPorBusqueda

    @Test
    fun `ejecutar caso de uso - retorno exitoso`() = runBlocking {
        val texto = "Mede"
        val clave = "claveSecreta"

        Mockito.`when`(iLocalizador.busquedaUbicaciones(apiKey = clave, query = texto))
            .thenReturn(Result.success(listOf(LocalizadorBase(ciudad = "Medellin", pais = "Colombia", region = "Antioquia"))))
        val result = obtenerUbicacionesPorBusqueda.invoke(texto, clave)
        Truth.assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `ejecutar caso de uso - retorno exitoso, sin coincidencias`() = runBlocking {
        val texto = "Mede"
        val clave = "claveSecreta"

        Mockito.`when`(iLocalizador.busquedaUbicaciones(apiKey = clave, query = texto))
            .thenReturn(Result.success(listOf()))
        val resultado = obtenerUbicacionesPorBusqueda.invoke(texto, clave)
        Truth.assertThat(resultado.isSuccess).isTrue()
    }
}