package com.loskon.features.wallpaper.presentation

import android.graphics.Bitmap

sealed class WallpaperState {
    data class Success(val bitmap: Bitmap) : WallpaperState()
    object Loading : WallpaperState()
    object Failure : WallpaperState()
    object NoInternet : WallpaperState()
}