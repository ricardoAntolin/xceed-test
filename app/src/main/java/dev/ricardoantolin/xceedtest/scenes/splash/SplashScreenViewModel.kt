package dev.ricardoantolin.xceedtest.scenes.splash

import dev.ricardoantolin.xceedtest.scenes.common.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val navigator: SplashNavigator
): BaseViewModel() {
    companion object {
        private const val SECONDS_IN_SPLASH: Long = 4
    }

    fun onCreate(activity: SplashScreenActivity) {
        navigator.loadSplashFragment(activity)
        Observable.timer(SECONDS_IN_SPLASH, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribeBy { navigator.goToListActivity(activity) }
            .addTo(disposables)
    }
}