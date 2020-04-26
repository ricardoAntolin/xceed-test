package dev.ricardoantolin.xceedtest

import dev.ricardoantolin.xceedtest.domain.executors.PostExecutionThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread
@Inject
constructor() : PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}