package dev.ricardoantolin.xceedtest.domain.executors

import io.reactivex.rxjava3.core.Scheduler
import java.util.concurrent.Executor


interface ThreadExecutor : Executor {
    fun getScheduler(): Scheduler
}