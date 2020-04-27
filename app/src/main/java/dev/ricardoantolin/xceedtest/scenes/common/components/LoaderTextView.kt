package dev.ricardoantolin.xceedtest.scenes.common.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.extensions.getCompatColor
import java.lang.ref.WeakReference

class LoaderTextView: AppCompatTextView, LoaderView {

    override var isLoaderEnabled: Boolean = true
    override var useGradient: Boolean = false
    override var loaderWidth: Int = 0
    override var loaderHeight: Int = 0

    private var loaderControllerRef: WeakReference<LoaderController>? = null
    private var loaderController: LoaderController?
        get() = loaderControllerRef?.get()
        set(value) {
            value?.let { loaderControllerRef = WeakReference<LoaderController>(value) }
        }

    constructor(context: Context): this(context, null, android.R.attr.textViewStyle)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, android.R.attr.textViewStyle)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int): super(context, attrs,
            defStyleAttr) {
        initViews(context, attrs)
    }

    private fun initViews(context: Context, attrs: AttributeSet?) {
        loaderController = LoaderController(context = context.applicationContext, loaderView = this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoaderTextView, 0, 0)
        loaderWidth = typedArray.getDimensionPixelSize(R.styleable.LoaderTextView_ltv_width, 0)
        loaderHeight = typedArray.getDimensionPixelSize(R.styleable.LoaderTextView_ltv_height, 0)
        useGradient = typedArray.getBoolean(R.styleable.LoaderTextView_ltv_useGradient, false)
        typedArray.recycle()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (isLoaderEnabled) {
            loaderController?.onSizeChanged()
        }
    }

    override fun hideLoader() {
        loaderController?.stopLoading()
    }

    override fun showLoader() {
        if (!text.isNullOrEmpty()) {
            text = ""
            loaderController?.startLoading()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        loaderController?.onDrawRect(canvas,
                compoundPaddingLeft.toFloat(),
                compoundPaddingTop.toFloat(),
                compoundPaddingRight.toFloat(),
                compoundPaddingBottom.toFloat())
    }

    override fun setText(text: CharSequence?, type: BufferType) {
        super.setText(text, type)
        loaderController?.stopLoading()
    }

    override fun setRectColor(rectPaint: Paint) {
        rectPaint.color = context.getCompatColor(R.color.loader_background)
    }

    override fun valueSet(): Boolean {
        return !text.isNullOrEmpty()
    }
}