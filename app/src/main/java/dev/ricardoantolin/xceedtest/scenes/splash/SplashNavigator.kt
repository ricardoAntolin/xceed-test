package dev.ricardoantolin.xceedtest.scenes.splash

import androidx.appcompat.app.AppCompatActivity
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.abstraction.loadFragment

interface SplashNavigator {
    fun loadSplashFragment()
}

class SplashNavigatorImpl(
    private val activity: AppCompatActivity
): SplashNavigator {
    override fun loadSplashFragment() {
        activity.supportFragmentManager
            .loadFragment(
                fragment = SplashScreenFragment(R.layout.splash_screen_fragment),
                containerIdRes = R.id.splashContainer,
                addToBackStack = false
            )
    }
}