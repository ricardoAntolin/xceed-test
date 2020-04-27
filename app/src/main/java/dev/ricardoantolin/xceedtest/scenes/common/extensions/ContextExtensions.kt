package dev.ricardoantolin.xceedtest.scenes.common.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getCompatColor(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}