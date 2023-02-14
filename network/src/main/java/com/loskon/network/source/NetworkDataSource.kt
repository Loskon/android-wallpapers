package com.loskon.network.source

import com.loskon.network.api.PixabayApi
import com.loskon.network.dto.WallpaperDto

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
}