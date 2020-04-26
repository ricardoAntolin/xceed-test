package dev.ricardoantolin.xceedtest.di.modules.fragments

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dev.ricardoantolin.xceedtest.scenes.splash.SplashNavigator
import dev.ricardoantolin.xceedtest.scenes.splash.SplashNavigatorImpl
import dev.ricardoantolin.xceedtest.scenes.splash.SplashScreenFragment

@Module
abstract class SplashScreenActivityFragmentBuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeSplashScreenFragmentInjector(): SplashScreenFragment
}