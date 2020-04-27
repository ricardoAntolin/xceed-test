package dev.ricardoantolin.xceedtest.scenes.common.components

import android.graphics.Paint

interface LoaderView {

    var isLoaderEnabled: Boolean
    var useGradient: Boolean
    var loaderWidth: Int
    var loaderHeight: Int

    fun setRectColor(rectPaint: Paint)
    fun hideLoader()
    fun showLoader()
    fun invalidate()
    fun valueSet(): Boolean
}