package dev.ricardoantolin.xceedtest.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dev.ricardoantolin.xceedtest.UIThread
import dev.ricardoantolin.xceedtest.domain.executors.PostExecutionThread

@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindApplicationContext(application: Application): Context

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread
}