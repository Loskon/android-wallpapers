package com.loskon.features.wallpaper.presentation

import android.content.Context
import android.graphics.Bitmap
import com.loskon.base.presentation.viewmodel.BaseViewModel
import com.loskon.features.wallpaper.domain.WallpaperInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WallpaperViewModel(
    private val wallpaperInteractor: WallpaperInteractor
): BaseViewModel() {

    private val wallpaperState = MutableStateFlow<WallpaperState>(WallpaperState.Loading)
    private val wallpaperBitmap = MutableStateFlow<Bitmap?>(null)
    val getWallpaperState get() = wallpaperState.asStateFlow()
    val getWallpaperBitmap get() = wallpaperBitmap.asStateFlow()

    private var job: Job? = null

    fun getWallpaperBitmap(context: Context, category: String) {
        job?.cancel()
        job = launchErrorJob(
            errorBlock = { wallpaperState.tryEmit(WallpaperState.Failure) }
        ) {
            wallpaperState.emit(WallpaperState.Loading)

            val bitmap = wallpaperInteractor.getWallpaperBitmap(context, category)
            wallpaperBitmap.emit(bitmap)
            wallpaperState.emit(WallpaperState.Success(bitmap))
        }
    }
}