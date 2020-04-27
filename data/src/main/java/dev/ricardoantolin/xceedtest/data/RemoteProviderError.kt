package dev.ricardoantolin.xceedtest.data

sealed class RemoteProviderError(message: String?): Throwable(message){
    data class Unknown(override val message: String?): RemoteProviderError(message)
    data class HttpError(val httpCode: Int, override val message: String?): RemoteProviderError(message)
}