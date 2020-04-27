package dev.ricardoantolin.xceedtest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.ricardoantolin.xceedtest.di.ViewModelKey
import dev.ricardoantolin.xceedtest.scenes.common.ViewModelFactory
import dev.ricardoantolin.xceedtest.scenes.list.CharacterListViewModel
import dev.ricardoantolin.xceedtest.scenes.splash.SplashScreenViewModel

@Module
abstract class ViewModelsModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindCharacterListViewModel(characterListViewModel: CharacterListViewModel): ViewModel

}