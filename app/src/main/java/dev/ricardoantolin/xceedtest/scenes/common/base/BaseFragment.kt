package dev.ricardoantolin.xceedtest.scenes.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

abstract class BaseFragment(@LayoutRes layoutIdRes: Int): DaggerFragment(layoutIdRes) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if (this is ViewModelBindable) bindViewModel()
        super.onActivityCreated(savedInstanceState)
    }
}