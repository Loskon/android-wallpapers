package com.loskon.features.wallpaper.domain

import android.content.Context
import android.graphics.Bitmap

interface WallpaperRepository {
    suspend fun getWallpaperBitmap(context: Context, imageUrl: String): Bitmap
}