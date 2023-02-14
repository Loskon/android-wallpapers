package com.loskon.network.api

import com.loskon.network.BuildConfig
import com.loskon.network.dto.ImagesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET(".")
    suspend fun getWallpapers(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("image_type") imageType: String = "photo",
        @Query("orientation") orientation: String = "vertical",
        @Query("category") category: String = "fashion"
    ): Response<ImagesDto>
}