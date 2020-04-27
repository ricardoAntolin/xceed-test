package dev.ricardoantolin.xceedtest.scenes.splash

import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.extensions.navigateToActivity
import dev.ricardoantolin.xceedtest.scenes.common.extensions.pushFragment
import dev.ricardoantolin.xceedtest.scenes.list.CharacterListActivity

fun SplashScreenActivity.loadSplashFragment() {
    pushFragment(
        fragment = SplashScreenFragment(),
        contentFrame = R.id.splashContainer
    )
}

fun SplashScreenActivity.goToListActivity() {
    navigateToActivity(CharacterListActivity::class.java)
}