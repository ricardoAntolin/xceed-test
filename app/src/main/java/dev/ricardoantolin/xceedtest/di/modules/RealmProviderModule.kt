package dev.ricardoantolin.xceedtest.di.modules

import dagger.Binds
import dagger.Module
import dev.ricardoantolin.xceedtest.data.characters.CharacterStorageProvider
import dev.ricardoantolin.xceedtest.realmprovider.characters.CharactersRealmProvider

@Module
abstract class RealmProviderModule {
    @Binds
    abstract fun bindCharacterRealmProvider(charactersRealmProvider: CharactersRealmProvider): CharacterStorageProvider
}