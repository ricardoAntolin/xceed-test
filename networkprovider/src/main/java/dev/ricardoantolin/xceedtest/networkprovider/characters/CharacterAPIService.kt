package dev.ricardoantolin.xceedtest.networkprovider.characters

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPIService {
    @GET("people")
    fun getPeople(
        @Query("page") page: Int,
        @Query("search") search: String
    ): Single<CharacterResponse>
}