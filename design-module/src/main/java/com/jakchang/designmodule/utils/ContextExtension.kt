package com.jakchang.deisgnlibrary.utils

import android.content.Context

internal fun Context.getPxToDp(value: Float): Float {
    val density = this.resources.displayMetrics.density
    return value / density
}

internal fun Context.getDpToPx(value: Float): Float {
    val density = this.resources.displayMetrics.density
    return value * density
}