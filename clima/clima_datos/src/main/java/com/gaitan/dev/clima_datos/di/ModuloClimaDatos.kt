package com.gaitan.dev.clima_datos.di

import com.gaitan.dev.clima_datos.fuente.remoto.RutaClimaModulo
import com.gaitan.dev.clima_datos.repositorio.ILocalizadorImpl
import com.gaitan.dev.clima_dominio.repositorio.ILocalizador
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloClimaDatos {

    @Provides
    @Singleton
    fun proveedorOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    fun proveedorRetrofit(cliente: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RutaClimaModulo.URL_PRINCIPAL)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun proveedorServiciosClima(retrofit: Retrofit): RutaClimaModulo {
        return retrofit.create(RutaClimaModulo::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: RutaClimaModulo
    ): ILocalizador {
        return ILocalizadorImpl(
            api = api
        )
    }

}