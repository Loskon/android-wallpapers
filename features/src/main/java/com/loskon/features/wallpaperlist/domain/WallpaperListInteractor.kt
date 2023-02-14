package com.loskon.features.wallpaperlist.domain

import com.loskon.features.model.WallpaperModel

class WallpaperListInteractor(
    private val wallpaperListRepository: WallpaperListRepository
) {
    suspend fun getWallpapers(category: String): List<WallpaperModel> {
        return wallpaperListRepository.getWallpapers(category)
    }
}