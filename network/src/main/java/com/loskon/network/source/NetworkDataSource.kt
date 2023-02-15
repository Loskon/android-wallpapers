package com.loskon.network.source

import android.content.Context
import android.graphics.Bitmap
import com.loskon.network.api.PixabayApi
import com.loskon.network.dto.WallpaperDto
import com.loskon.network.imageloader.GlideApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSource(
    private val pixabayApi: PixabayApi
) {

    suspend fun getWallpapers(category: String): List<WallpaperDto> {
        val response = pixabayApi.getWallpapers(category = category)

        return if (response.isSuccessful) {
            response.body()?.hits ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getWallpaperBitmap(context: Context, imageUrl: String): Bitmap {
        return withContext(Dispatchers.IO) {
            GlideApp
                .with(context)
                .asBitmap()
                .load(imageUrl)
                .submit()
                .get()
        }
    }
}