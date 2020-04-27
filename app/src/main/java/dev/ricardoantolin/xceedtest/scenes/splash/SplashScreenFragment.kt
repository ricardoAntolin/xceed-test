package dev.ricardoantolin.xceedtest.scenes.splash

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseFragment
import kotlinx.android.synthetic.main.splash_screen_fragment.*

class SplashScreenFragment: BaseFragment(R.layout.splash_screen_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            Glide.with(it)
                .load(R.drawable.star_wars_splash)
                .into(splashGif)
        }
    }
}