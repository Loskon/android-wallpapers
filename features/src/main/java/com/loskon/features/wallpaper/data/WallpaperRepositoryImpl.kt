package com.loskon.features.wallpaper.data

import android.content.Context
import android.graphics.Bitmap
import com.loskon.features.wallpaper.domain.WallpaperRepository
import com.loskon.network.source.NetworkDataSource

class WallpaperRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : WallpaperRepository {
    override suspend fun getWallpaperBitmap(context: Context, imageUrl: String): Bitmap {
        return networkDataSource.getWallpaperBitmap(context, imageUrl)
    }
}