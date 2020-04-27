package dev.ricardoantolin.xceedtest.scenes.common.extensions

import android.os.SystemClock
import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.toggleVisibility() {
    when (visibility) {
        View.GONE -> visible()
        else -> gone()
    }
}

class SensitiveClickListener(
    private var threshold: Int = 500,
    private val onCLick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < threshold) {
            return
        }

        lastTimeClicked = SystemClock.elapsedRealtime()
        onCLick(view)
    }
}

fun View.setSensitiveClickListener(onClick: (View) -> Unit) {
    this.setOnClickListener(
        SensitiveClickListener { onClick(it) }
    )
}