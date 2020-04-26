package dev.ricardoantolin.xceedtest.di.modules

import dagger.Binds
import dagger.Module
import dev.ricardoantolin.xceedtest.data.executors.JobExecutor
import dev.ricardoantolin.xceedtest.domain.executors.ThreadExecutor

@Module
abstract class DataModule {
    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}