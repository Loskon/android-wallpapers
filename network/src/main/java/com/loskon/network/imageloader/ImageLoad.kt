package com.loskon.network.imageloader

import android.widget.ImageView
import com.loskon.network.R

object ImageLoad {
    fun load(view: ImageView, url: String) {
        GlideApp
            .with(view)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .into(view)
    }
}