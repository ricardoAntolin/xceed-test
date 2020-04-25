package dev.ricardoantolin.xceedtest.scenes.splash

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity(@LayoutRes layoutIdRes: Int): AppCompatActivity(layoutIdRes) {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}