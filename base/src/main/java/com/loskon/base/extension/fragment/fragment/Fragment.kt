package com.loskon.base.extension.fragment.fragment

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment

fun Fragment.getDrawable(@DrawableRes drawableId: Int): Drawable? {
    return AppCompatResources.getDrawable(requireContext(), drawableId)
}

fun Fragment.requireDrawable(@DrawableRes drawableId: Int): Drawable {
    return getDrawable(drawableId) ?: throw NullPointerException("Fragment $this could not find the drawable.")
}