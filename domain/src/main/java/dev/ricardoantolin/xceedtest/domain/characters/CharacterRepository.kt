package dev.ricardoantolin.xceedtest.domain.characters

import io.reactivex.Completable
import io.reactivex.Flowable

interface CharacterRepository {
    fun updateCharacters(page: Int, search: String): Completable
    fun bindCharacters(search: String): Flowable<List<Character>>
}