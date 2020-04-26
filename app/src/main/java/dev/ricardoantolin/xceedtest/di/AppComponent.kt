package dev.ricardoantolin.xceedtest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.ricardoantolin.xceedtest.App
import dev.ricardoantolin.xceedtest.di.modules.ActivitiesModule
import dev.ricardoantolin.xceedtest.di.modules.DomainModule
import dev.ricardoantolin.xceedtest.di.modules.NetworkProviderModule
import dev.ricardoantolin.xceedtest.di.modules.ApplicationModule
import dev.ricardoantolin.xceedtest.di.modules.DataModule
import dev.ricardoantolin.xceedtest.di.modules.RealmProviderModule
import dev.ricardoantolin.xceedtest.di.modules.ViewModelsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    DomainModule::class,
    DataModule::class,
    NetworkProviderModule::class,
    RealmProviderModule::class,
    ActivitiesModule::class,
    ViewModelsModule::class
])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}