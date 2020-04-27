package dev.ricardoantolin.xceedtest

import dev.ricardoantolin.xceedtest.domain.executors.PostExecutionThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread
@Inject
constructor() : PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}