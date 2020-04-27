package dev.ricardoantolin.xceedtest.scenes.common.components

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.view.animation.LinearInterpolator
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.extensions.getCompatColor
import java.lang.ref.WeakReference


internal class LoaderController(loaderView: LoaderView, private val context: Context) {

    private val MAX_COLOR_CONSTANT_VALUE = 255
    private val ANIMATION_CYCLE_DURATION = 750

    private var linearGradient: LinearGradient? = null
    private var progress: Float = 0f
    private lateinit var rectPaint: Paint
    private lateinit var valueAnimator: ValueAnimator

    private val loaderViewRef = WeakReference(loaderView)
    private val loaderView
        get() = loaderViewRef.get()!!

    init {
        initView()
    }

    private fun initView() {
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        loaderView.setRectColor(rectPaint)
        valueAnimator = createValueAnimator(
                begin = 1f,
                end = 0.5f,
                repeatCountParam = ObjectAnimator.INFINITE)
    }

    fun onDrawRect(canvas: Canvas,
                   paddingLeft: Float = 0f,
                   paddingTop: Float = 0f,
                   paddingRight: Float = 0f,
                   paddingBottom: Float = 0f) {
        if (!shouldUseLoader()) return

        rectPaint.alpha = (progress * MAX_COLOR_CONSTANT_VALUE).toInt()

        if (loaderView.useGradient) {
            prepareGradient(canvas.width.toFloat())
        }

        val width = if (loaderView.loaderWidth > 0) loaderView.loaderWidth else canvas.width
        val height = if (loaderView.loaderHeight > 0) loaderView.loaderHeight else canvas.height

        canvas.drawRoundRect(
                RectF(
                        0 + paddingLeft,
                        paddingTop,
                        width - paddingRight,
                        height - paddingBottom
                ),
                canvas.height.toFloat(),
                canvas.height.toFloat(),
                rectPaint)
    }

    fun onDrawRoundRect(
            canvas: Canvas,
            paddingLeft: Float = 0f,
            paddingTop: Float = 0f,
            paddingRight: Float = 0f,
            paddingBottom: Float = 0f,
            radious: Float = 0f
    ) {
        if (!shouldUseLoader()) return

        rectPaint.alpha = (progress * MAX_COLOR_CONSTANT_VALUE).toInt()

        if (loaderView.useGradient) {
            prepareGradient(canvas.width.toFloat())
        }

        val width = if (loaderView.loaderWidth > 0) loaderView.loaderWidth else canvas.width
        val height = if (loaderView.loaderHeight > 0) loaderView.loaderHeight else canvas.height

        canvas.drawRoundRect(
                RectF(
                        0 + paddingLeft,
                        paddingTop,
                        width - paddingRight,
                        height - paddingBottom
                ),
                radious,
                radious,
                rectPaint)
    }

    fun onDrawCircle(canvas: Canvas,
                     paddingLeft: Float = 0f,
                     paddingTop: Float = 0f) {
        if (!shouldUseLoader()) return

        rectPaint.alpha = (progress * MAX_COLOR_CONSTANT_VALUE).toInt()

        if (loaderView.useGradient) {
            prepareGradient(canvas.width.toFloat())
        }

        val width = if (loaderView.loaderWidth > 0) loaderView.loaderWidth else canvas.width

        canvas.drawCircle((canvas.height / 2) + paddingTop,
                (width / 2) + paddingLeft,
                (width / 2).toFloat(),
                rectPaint)
    }

    fun onSizeChanged() {
        linearGradient = null
        startLoading()
    }

    private fun prepareGradient(width: Float) {
        if (linearGradient == null) {
            linearGradient = LinearGradient(0f, 0f, width, 0f, rectPaint.color,
                    context.getCompatColor(R.color.loader_background),
                    Shader.TileMode.MIRROR)
        }
        rectPaint.shader = linearGradient
    }

    fun startLoading() {
        if (!loaderView.valueSet()) {
            valueAnimator.cancel()
            initView()
            valueAnimator.start()
        }
    }

    fun stopLoading() {
        valueAnimator.cancel()
        valueAnimator = createValueAnimator(progress, 0f, 0)
        valueAnimator.start()
    }

    private fun createValueAnimator(begin: Float, end: Float, repeatCountParam: Int): ValueAnimator {
        val valueAnimator = ValueAnimator.ofFloat(begin, end)
        with(valueAnimator) {
            repeatCount = repeatCountParam
            duration = ANIMATION_CYCLE_DURATION.toLong()
            repeatMode = ValueAnimator.REVERSE
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                progress = animation.animatedValue as Float
                loaderViewRef.get()?.invalidate()
            }
        }
        return valueAnimator
    }

    private fun shouldUseLoader(): Boolean {
        return loaderView.isLoaderEnabled
    }
}