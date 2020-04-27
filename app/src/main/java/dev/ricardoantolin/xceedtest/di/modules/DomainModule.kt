package dev.ricardoantolin.xceedtest.di.modules

import dagger.Binds
import dagger.Module
import dev.ricardoantolin.xceedtest.domain.characters.BindCharacter
import dev.ricardoantolin.xceedtest.domain.characters.BindCharacterUseCase
import dev.ricardoantolin.xceedtest.domain.characters.UpdateCharacter
import dev.ricardoantolin.xceedtest.domain.characters.UpdateCharacterUseCase

@Module
abstract class DomainModule {
    @Binds
    abstract fun bindBindCharacterUseCase(useCase: BindCharacter): BindCharacterUseCase

    @Binds
    abstract fun bindUpdateCharacterUseCase(useCase: UpdateCharacter): UpdateCharacterUseCase
}