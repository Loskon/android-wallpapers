package com.loskon.features.wallpaperlist.presentation

import com.loskon.base.presentation.viewmodel.BaseViewModel
import com.loskon.features.wallpaperlist.domain.WallpaperListInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WallpaperListViewModel(
    private val wallpaperListInteractor: WallpaperListInteractor
) : BaseViewModel() {

    private val wallpaperListState = MutableStateFlow<WallpaperListState>(WallpaperListState.Loading)
    val getWallpaperListState get() = wallpaperListState.asStateFlow()

    private var job: Job? = null

    fun getWallpaperList(category: String) {
        job?.cancel()
        job = launchErrorJob(
            errorBlock = { wallpaperListState.tryEmit(WallpaperListState.Failure) }
        ) {
            wallpaperListState.emit(WallpaperListState.Loading)
            wallpaperListState.emit(WallpaperListState.Success(wallpaperListInteractor.getWallpapers(category)))
        }
    }
}