package dev.ricardoantolin.xceedtest.scenes.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.ricardoantolin.xceedtest.scenes.common.BaseActivity
import javax.inject.Inject
import dev.ricardoantolin.xceedtest.R

class SplashScreenActivity: BaseActivity() {
    override val layoutResId: Int = R.layout.splash_screen_activity

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: SplashNavigator
    lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.loadSplashFragment(this)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SplashScreenViewModel::class.java)
        viewModel.onCreate().observe(this, Observer {
            navigator.goToListActivity(this)
        })
    }
}