package com.loskon.features.wallpaperlist.presentation

import com.loskon.features.model.WallpaperModel

sealed class WallpaperListState {
    data class Success(val wallpapers: List<WallpaperModel>) : WallpaperListState()
    object Loading : WallpaperListState()
    object Failure : WallpaperListState()
    object NoInternet : WallpaperListState()
}