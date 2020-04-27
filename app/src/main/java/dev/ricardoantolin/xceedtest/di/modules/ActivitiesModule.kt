package dev.ricardoantolin.xceedtest.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.ricardoantolin.xceedtest.scenes.detail.CharacterDetailActivity
import dev.ricardoantolin.xceedtest.scenes.list.CharacterListActivity
import dev.ricardoantolin.xceedtest.scenes.splash.SplashScreenActivity

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = [SplashScreenActivityFragmentBuildersModule::class])
    abstract fun contributeSplashActivityInjector(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [CharacterListActivityFragmentBuildersModule::class])
    abstract fun contributeCharacterListActivityInjector(): CharacterListActivity

    @ContributesAndroidInjector(modules = [CharacterDetailActivityFragmentBuildersModule::class])
    abstract fun contributeCharacterDetailActivityInjector(): CharacterDetailActivity
}