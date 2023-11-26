package com.gaitan.dev.clima_datos.repositorio

import com.gaitan.dev.clima_datos.fuente.remoto.RutaClimaModulo
import com.gaitan.dev.clima_datos.fuente.remoto.localizadorRepuestaCorrecta
import com.gaitan.dev.clima_datos.fuente.remoto.localizadorRepuestaIncorrecta
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ILocalizadorApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var iLocalizadorImpl: ILocalizadorImpl
    private lateinit var rutaClimaModulo: RutaClimaModulo

    @Before
    fun configuracionInicial() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        rutaClimaModulo = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(RutaClimaModulo::class.java)
        iLocalizadorImpl = ILocalizadorImpl(
            api = rutaClimaModulo
        )
    }

    @Test
    fun `buscar ubicaciones, paremetro de busqueda valido y retorno exitoso`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(localizadorRepuestaCorrecta)
        )
        val result = iLocalizadorImpl.busquedaUbicaciones(apiKey = "valorSecreto", query = "Mede")

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `buscar ubicaciones, paremetro de busqueda valido y retorno fallido`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody(localizadorRepuestaIncorrecta)
        )
        val resultado = iLocalizadorImpl.busquedaUbicaciones(apiKey = "valorSecreto", query = "Mede")

        assertThat(resultado.isFailure).isTrue()
    }


    @After
    fun configuracionFinal(){
        mockWebServer.shutdown()
    }
}