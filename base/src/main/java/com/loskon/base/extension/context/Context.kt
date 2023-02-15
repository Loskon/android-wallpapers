package com.loskon.base.extension.context

import android.annotation.SuppressLint
import android.content.Context

val Context.navBarHeightInDp
    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    get() = run {
        val resourceId = resources.getIdentifier(
            "navigation_bar_height",
            "dimen",
            "android"
        )
        resources.getDimensionPixelSize(resourceId) / resources.displayMetrics.density
    }