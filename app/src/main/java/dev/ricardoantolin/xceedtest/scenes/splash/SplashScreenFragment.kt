package dev.ricardoantolin.xceedtest.scenes.splash

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.BaseFragment
import kotlinx.android.synthetic.main.splash_screen_fragment.*

class SplashScreenFragment(@LayoutRes layoutIdRes: Int): BaseFragment(layoutIdRes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide.with(it)
                .load(R.drawable.star_wars_splash)
                .into(splashGif)
        }
    }
}