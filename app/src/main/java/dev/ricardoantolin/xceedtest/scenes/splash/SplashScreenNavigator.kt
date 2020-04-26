package dev.ricardoantolin.xceedtest.scenes.splash

import androidx.appcompat.app.AppCompatActivity
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.loadFragment
import javax.inject.Inject

interface SplashNavigator {
    fun loadSplashFragment(activity: AppCompatActivity)
    fun goToListActivity(activity: AppCompatActivity)
}

class SplashNavigatorImpl @Inject constructor(): SplashNavigator {
    override fun loadSplashFragment(activity: AppCompatActivity) {
        activity.supportFragmentManager
            .loadFragment(
                fragment = SplashScreenFragment(R.layout.splash_screen_fragment),
                containerIdRes = R.id.splashContainer,
                addToBackStack = false
            )
    }

    override fun goToListActivity(activity: AppCompatActivity) {

    }
}