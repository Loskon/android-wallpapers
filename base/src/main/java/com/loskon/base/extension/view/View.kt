package com.loskon.base.extension.view

import android.content.res.Resources
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateMargins
import kotlin.math.roundToInt

fun View.setDebounceClickListener(debounceTime: Long = 600L, action: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {

        private var lastClickTime: Long = 0

        override fun onClick(view: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                return
            } else {
                action()
            }

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.setMargins(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        left?.run { params.updateMargins(left = left.dp) }
        top?.run { params.updateMargins(top = top.dp) }
        right?.run { params.updateMargins(right = right.dp) }
        bottom?.run { params.updateMargins(bottom = bottom.dp) }
    }
}

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).roundToInt()