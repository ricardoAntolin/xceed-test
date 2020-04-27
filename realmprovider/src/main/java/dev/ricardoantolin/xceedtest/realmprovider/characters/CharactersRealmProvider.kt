package dev.ricardoantolin.xceedtest.realmprovider.characters

import dev.ricardoantolin.xceedtest.data.characters.CharacterEntity
import dev.ricardoantolin.xceedtest.data.characters.CharacterStorageProvider
import dev.ricardoantolin.xceedtest.realmprovider.RealmService
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CharactersRealmService: RealmService<CharacterRMObject, String>()

class CharactersRealmProvider @Inject constructor(): CharacterStorageProvider {
    private val service: CharactersRealmService = CharactersRealmService()
    override fun findCharacters(search: String): Flowable<List<CharacterEntity>> =
        service.findAll<CharacterRMObject>().map { result ->
            if (search.isNotBlank()) {
                result.mapToEntity()
                    .filter { it.name.toLowerCase().startsWith(search.toLowerCase()) }
            }
            else result.mapToEntity()
        }.distinctUntilChanged()

    override fun saveCharacters(characters: List<CharacterEntity>): Completable =
        service.save(characters.mapToRealm())
}