package dev.ricardoantolin.xceedtest.data.characters

import io.reactivex.Completable
import io.reactivex.Flowable

interface CharacterStorageProvider {
    fun findCharacters(search: String): Flowable<List<CharacterEntity>>
    fun saveCharacters(characters: List<CharacterEntity>): Completable
}