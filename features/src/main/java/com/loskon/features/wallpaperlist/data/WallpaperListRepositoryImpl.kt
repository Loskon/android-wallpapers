package com.loskon.features.wallpaperlist.data

import com.loskon.features.model.WallpaperModel
import com.loskon.features.model.toWallpaperModel
import com.loskon.features.wallpaperlist.domain.WallpaperListRepository
import com.loskon.network.source.NetworkDataSource

class WallpaperListRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : WallpaperListRepository {

    override suspend fun getWallpapers(category: String): List<WallpaperModel> {
        return networkDataSource.getWallpapers(category).map { it.toWallpaperModel() }
    }
}