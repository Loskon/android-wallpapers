package com.loskon.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesDto(
    @Json(name = "total") val total: Long?,
    @Json(name = "totalHits") val totalHits: Long?,
    @Json(name = "hits") val hits: List<WallpaperDto>
)

@JsonClass(generateAdapter = true)
data class WallpaperDto(
    @Json(name = "id") val id: Long?,
    @Json(name = "webformatURL") val webformatURL: String?
)