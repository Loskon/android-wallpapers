package com.loskon.features.wallpaper.domain

import android.content.Context
import android.graphics.Bitmap

class WallpaperInteractor(
    private val wallpaperRepository: WallpaperRepository
) {

    suspend fun getWallpaperBitmap(context: Context, imageUrl: String): Bitmap {
        return wallpaperRepository.getWallpaperBitmap(context, imageUrl)
    }
}