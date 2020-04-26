package dev.ricardoantolin.xceedtest.scenes.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import dev.ricardoantolin.xceedtest.scenes.common.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(): BaseViewModel() {
    companion object {
        private const val SECONDS_IN_SPLASH: Long = 4
    }

    fun onCreate(): LiveData<Long> = LiveDataReactiveStreams.fromPublisher (
            Observable.timer(SECONDS_IN_SPLASH, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .toFlowable(BackpressureStrategy.BUFFER)
    )
}