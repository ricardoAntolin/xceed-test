package dev.ricardoantolin.xceedtest.data.characters

import io.reactivex.Single

interface CharacterRemoteProvider {
    fun fetchRemoteCharacters(page: Int, name: String): Single<List<CharacterEntity>>
}