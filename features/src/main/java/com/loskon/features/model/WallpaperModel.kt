package com.loskon.features.model

import com.loskon.network.dto.WallpaperDto

data class WallpaperModel(
    val id: Long?,
    val webformatURL: String?
)

fun WallpaperDto.toWallpaperModel(): WallpaperModel {
    return WallpaperModel(
        id = id,
        webformatURL = webformatURL
    )
}