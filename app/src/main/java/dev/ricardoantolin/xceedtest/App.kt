package dev.ricardoantolin.xceedtest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.ricardoantolin.xceedtest.di.DaggerAppComponent


class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@App) }
    }
}