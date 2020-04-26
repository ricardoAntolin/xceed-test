package dev.ricardoantolin.xceedtest.scenes.common

import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

abstract class BaseFragment(@LayoutRes layoutIdRes: Int): DaggerFragment(layoutIdRes)