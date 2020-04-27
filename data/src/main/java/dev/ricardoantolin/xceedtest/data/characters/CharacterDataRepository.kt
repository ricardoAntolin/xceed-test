package dev.ricardoantolin.xceedtest.data.characters

import dev.ricardoantolin.xceedtest.domain.characters.Character
import dev.ricardoantolin.xceedtest.domain.characters.CharacterRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CharacterDataRepository @Inject constructor(
    private val remoteProvider: CharacterRemoteProvider,
    private val storageProvider: CharacterStorageProvider
): CharacterRepository {
    override fun updateCharacters(page: Int, search: String): Completable =
            remoteProvider.fetchRemoteCharacters(page, search)
                .flatMapCompletable { storageProvider.saveCharacters(it) }

    override fun bindCharacters(search: String): Flowable<List<Character>> =
        storageProvider.findCharacters(search)
            .map { it.mapToDomain() }
            .doOnEach { print(it.toString()) }
}