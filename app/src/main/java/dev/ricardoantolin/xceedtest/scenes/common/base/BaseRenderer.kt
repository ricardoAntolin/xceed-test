package dev.ricardoantolin.xceedtest.scenes.common.base

import com.pedrogomez.renderers.Renderer

abstract class BaseRenderer<T> : Renderer<T>() {

    companion object {
        private const val NO_POSITION = -1
    }

    var position: Int = NO_POSITION
}
