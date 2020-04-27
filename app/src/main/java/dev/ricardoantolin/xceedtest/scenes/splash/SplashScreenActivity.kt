package dev.ricardoantolin.xceedtest.scenes.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseActivity
import javax.inject.Inject
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.base.ViewModelBindable

class SplashScreenActivity: BaseActivity(), ViewModelBindable {
    override val layoutResId: Int = R.layout.splash_screen_activity

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadSplashFragment()
    }

    override fun bindViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SplashScreenViewModel::class.java)

        viewModel.onCreate().observe(this, Observer {
            goToListActivity()
        })
    }
}