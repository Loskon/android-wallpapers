package com.loskon.network.imageloader

import android.widget.ImageView

object ImageLoad {
    fun load(view: ImageView, url: String) {
        GlideApp
            .with(view)
            .load(url)
            .into(view)
    }
}