package com.loskon.features.wallpaperlist.domain

import com.loskon.features.model.WallpaperModel

interface WallpaperListRepository {
    suspend fun getWallpapers(category: String): List<WallpaperModel>
}