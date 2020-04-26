package dev.ricardoantolin.xceedtest.domain.executors

import io.reactivex.rxjava3.core.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}
