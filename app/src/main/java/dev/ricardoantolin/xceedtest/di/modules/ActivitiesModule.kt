package dev.ricardoantolin.xceedtest.di.modules

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dev.ricardoantolin.xceedtest.di.modules.fragments.SplashActivityModule
import dev.ricardoantolin.xceedtest.di.modules.fragments.SplashScreenActivityFragmentBuildersModule
import dev.ricardoantolin.xceedtest.scenes.splash.SplashNavigator
import dev.ricardoantolin.xceedtest.scenes.splash.SplashNavigatorImpl
import dev.ricardoantolin.xceedtest.scenes.splash.SplashScreenActivity

@Module
abstract class ActivitiesModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun bindSplashNavigator(splashNavigator: SplashNavigatorImpl): SplashNavigator = splashNavigator
    }

    @ContributesAndroidInjector(modules = [
        SplashScreenActivityFragmentBuildersModule::class,
        SplashActivityModule::class
    ])
    abstract fun contributeSplashActivityInjector(): SplashScreenActivity
}