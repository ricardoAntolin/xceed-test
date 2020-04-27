package dev.ricardoantolin.xceedtest.domain.executors

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}
