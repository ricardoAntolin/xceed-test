package dev.ricardoantolin.xceedtest.di.modules

import dagger.Binds
import dagger.Module
import dev.ricardoantolin.xceedtest.data.characters.CharacterDataRepository
import dev.ricardoantolin.xceedtest.networkprovider.executors.JobExecutor
import dev.ricardoantolin.xceedtest.domain.characters.CharacterRepository
import dev.ricardoantolin.xceedtest.data.executors.ThreadExecutor

@Module
abstract class DataModule {
    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun bindCharacterRepository(characterRepository: CharacterDataRepository): CharacterRepository
}