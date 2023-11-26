package com.gaitan.dev.clima_datos.fuente.remoto.mapeador

import com.gaitan.dev.clima_datos.fuente.remoto.CondicionHoraDetalleDto
import com.gaitan.dev.clima_datos.fuente.remoto.DetalleActualDto
import com.gaitan.dev.clima_datos.fuente.remoto.DetalleUbicacionDto
import com.gaitan.dev.clima_datos.fuente.remoto.PronosticoDetalleDto
import com.gaitan.dev.clima_datos.fuente.remoto.PronosticoHoraDetalleDto
import com.gaitan.dev.clima_datos.fuente.remoto.PronosticoUbicacionDto
import com.gaitan.dev.clima_dominio.modelo.CondicionHoraDetalle
import com.gaitan.dev.clima_dominio.modelo.DetalleActual
import com.gaitan.dev.clima_dominio.modelo.DetalleUbicacion
import com.gaitan.dev.clima_dominio.modelo.PronosticoDetalle
import com.gaitan.dev.clima_dominio.modelo.PronosticoHoraDetalle
import com.gaitan.dev.clima_dominio.modelo.PronosticoUbicacion

fun mapDetalleUbicacionDtoToDetalleUbicacion(dto: DetalleUbicacionDto): DetalleUbicacion {
    return DetalleUbicacion(
        nombre = dto.location.name,
        region = dto.location.region,
        pais = dto.location.country,
        detalleUbicacionActual = mapDetalleActualDtoToDetalleActual(dto.current),
        pronostico = mapPronosticoUbicacionDtoToPronosticoUbicacion(dto.forecast)
    )
}

fun mapDetalleActualDtoToDetalleActual(dto: DetalleActualDto): DetalleActual {
    return DetalleActual(
        temperaturaC = dto.temp_c,
        condicionHoraDetalle = mapCondicionHoraDetalleDtoToCondicionHoraDetalle(dto.condition)
    )
}

fun mapCondicionHoraDetalleDtoToCondicionHoraDetalle(dto: CondicionHoraDetalleDto): CondicionHoraDetalle {
    return CondicionHoraDetalle(
        icono = dto.icon
    )
}

fun mapPronosticoUbicacionDtoToPronosticoUbicacion(dto: PronosticoUbicacionDto): PronosticoUbicacion {
    return PronosticoUbicacion(
        pronosticoPosterior = dto.forecastday.map { mapPronosticoDetalleDtoToPronosticoDetalle(it) }
    )
}

fun mapPronosticoDetalleDtoToPronosticoDetalle(dto: PronosticoDetalleDto): PronosticoDetalle {
    return PronosticoDetalle(
        fecha = dto.date,
        horaDia = dto.hour.map { mapPronosticoHoraDetalleDtoToPronosticoHoraDetalle(it) }
    )
}

fun mapPronosticoHoraDetalleDtoToPronosticoHoraDetalle(dto: PronosticoHoraDetalleDto): PronosticoHoraDetalle {
    return PronosticoHoraDetalle(
        temperaturaC = dto.temp_c,
        condicion = mapCondicionHoraDetalleDtoToCondicionHoraDetalle(dto.condition)
    )
}