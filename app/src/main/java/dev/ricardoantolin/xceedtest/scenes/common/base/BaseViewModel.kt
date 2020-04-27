package dev.ricardoantolin.xceedtest.scenes.common.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel()

interface ViewModelBindable {
    fun bindViewModel()
}