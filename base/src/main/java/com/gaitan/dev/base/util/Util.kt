package com.gaitan.dev.base.util
import com.gaitan.dev.base.util.base_dominio.errores.ErrorException
import com.google.gson.Gson
import retrofit2.HttpException

object Util {
    var mensajeError: String = ""
        private set

    fun obtenerCodigoError(error: Throwable?): Int {
        return try {
            when (error) {
                is HttpException -> {
                    val errorJsonString = error.response()?.errorBody()?.string()
                    val error = Gson().fromJson(errorJsonString, ErrorException::class.java)
                    mensajeError = error.message
                    error.errorCode
                }
                else -> 0
            }
        } catch (exception: java.lang.Exception) {
            return 0
        }
    }
}