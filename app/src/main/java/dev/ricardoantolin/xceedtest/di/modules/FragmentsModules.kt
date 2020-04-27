package dev.ricardoantolin.xceedtest.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.ricardoantolin.xceedtest.scenes.detail.CharacterDetailFragment
import dev.ricardoantolin.xceedtest.scenes.list.CharacterListFragment
import dev.ricardoantolin.xceedtest.scenes.splash.SplashScreenFragment

@Module
abstract class SplashScreenActivityFragmentBuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeSplashScreenFragmentInjector(): SplashScreenFragment
}

@Module
abstract class CharacterListActivityFragmentBuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeCharacterListFragmentInjector(): CharacterListFragment
}

@Module
abstract class CharacterDetailActivityFragmentBuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeCharacterDetailFragmentInjector(): CharacterDetailFragment
}

