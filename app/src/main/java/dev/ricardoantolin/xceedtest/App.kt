package dev.ricardoantolin.xceedtest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.ricardoantolin.xceedtest.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration


class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@App) }
    }

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        )
    }
}