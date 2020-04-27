package dev.ricardoantolin.xceedtest.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.ricardoantolin.xceedtest.BuildConfig
import dev.ricardoantolin.xceedtest.data.characters.CharacterRemoteProvider
import dev.ricardoantolin.xceedtest.networkprovider.RemoteServiceConfig
import dev.ricardoantolin.xceedtest.networkprovider.characters.CharacterNetworkProvider
import javax.inject.Singleton

@Module
abstract class NetworkProviderModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        fun provideRemoteServiceConfig(): RemoteServiceConfig = RemoteServiceConfig(
            debug = BuildConfig.DEBUG,
            baseUrl = BuildConfig.API_URL
        )
    }

    @Binds
    abstract fun bindCharacterRemoteProvider(characterRepository: CharacterNetworkProvider): CharacterRemoteProvider
}