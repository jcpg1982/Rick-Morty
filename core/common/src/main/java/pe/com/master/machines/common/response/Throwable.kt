package pe.com.master.machines.common.response

import retrofit2.HttpException
import pe.com.master.machines.common.response.ErrorCodes.Http.INTERNAL_SERVER
import pe.com.master.machines.common.response.ErrorCodes.Http.RESOURCE_NOT_FOUND
import pe.com.master.machines.common.response.ErrorCodes.Http.SERVICE_UNAVAILABLE
import pe.com.master.machines.common.response.ErrorCodes.Http.UNAUTHORIZED
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.toErrorType() = when (this) {
    is SocketTimeoutException -> ErrorType.Api.Timeout(this.message.orEmpty())
    is IOException -> ErrorType.Api.Network(this.message.orEmpty())
    is HttpException -> when (this.code()) {
        RESOURCE_NOT_FOUND -> ErrorType.Api.NotFound(this.message.orEmpty())
        INTERNAL_SERVER -> ErrorType.Api.Server(this.message.orEmpty())
        SERVICE_UNAVAILABLE -> ErrorType.Api.ServiceUnavailable(this.message.orEmpty())
        UNAUTHORIZED -> ErrorType.Api.Unauthorized(this.message.orEmpty())
        else -> ErrorType.Unknown(this.message ?: "Error desconocido")
    }

    else -> ErrorType.Unknown(this.message.orEmpty())
}