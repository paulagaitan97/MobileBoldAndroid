package com.gaitan.dev.base.util.base_dominio.errores

data class ErrorException(
    val errorCode: Int,
    val message: String,
)