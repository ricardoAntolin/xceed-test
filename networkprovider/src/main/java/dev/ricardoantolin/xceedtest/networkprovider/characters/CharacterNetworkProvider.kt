package dev.ricardoantolin.xceedtest.networkprovider.characters

import dev.ricardoantolin.xceedtest.data.RemoteProviderError
import dev.ricardoantolin.xceedtest.data.characters.CharacterEntity
import dev.ricardoantolin.xceedtest.data.characters.CharacterRemoteProvider
import dev.ricardoantolin.xceedtest.networkprovider.RemoteService
import dev.ricardoantolin.xceedtest.networkprovider.RemoteServiceConfig
import dev.ricardoantolin.xceedtest.networkprovider.executors.JobExecutor
import io.reactivex.Single
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

class CharacterNetworkProvider @Inject constructor(serviceConfig: RemoteServiceConfig):
    RemoteService<CharacterAPIService>(CharacterAPIService::class.java, serviceConfig),
    CharacterRemoteProvider {
    override fun fetchRemoteCharacters(page: Int, name: String): Single<List<CharacterEntity>> {
        return service.getPeople(page, name)
            .mapError()
            .map { it.results.mapToEntity() }
            .subscribeOn(JobExecutor().getScheduler())
    }
}

private fun Single<CharacterResponse>.mapError(): Single<CharacterResponse> {
    return onErrorResumeNext {
        if (it is HttpException && it.code() == HttpURLConnection.HTTP_NOT_FOUND)
            Single.just(CharacterResponse(listOf()))

        Single.error(when (it) {
            is HttpException -> RemoteProviderError.HttpError(it.code(), it.response().errorBody()?.string())
            else -> RemoteProviderError.Unknown(it.message)
        })
    }
}
