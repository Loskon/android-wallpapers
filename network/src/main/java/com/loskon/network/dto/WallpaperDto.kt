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
    @Json(name = "pageURL") val pageURL: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "tags") val tags: String?,
    @Json(name = "previewURL") val previewURL: String?,
    @Json(name = "previewWidth") val previewWidth: Int?,
    @Json(name = "previewHeight") val previewHeight: Int?,
    @Json(name = "webformatURL") val webformatURL: String?,
    @Json(name = "webformatWidth") val webformatWidth: Int?,
    @Json(name = "webformatHeight") val webformatHeight: Int?,
    @Json(name = "largeImageURL") val largeImageURL: String?,
    @Json(name = "imageWidth") val imageWidth: Int?,
    @Json(name = "imageHeight") val imageHeight: Int?,
    @Json(name = "imageSize") val imageSize: Int?,
    @Json(name = "views") val views: Int?,
    @Json(name = "downloads") val downloads: Int?,
    @Json(name = "collections") val collections: Int?,
    @Json(name = "likes") val likes: Int?,
    @Json(name = "comments") val comments: Int?,
    @Json(name = "user_id") val userId: Int?,
    @Json(name = "user") val user: String?,
    @Json(name = "userImageURL") val userImageURL: String?
)