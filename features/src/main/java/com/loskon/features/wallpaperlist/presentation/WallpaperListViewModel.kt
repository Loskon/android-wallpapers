package com.loskon.features.wallpaperlist.presentation

import com.loskon.base.presentation.viewmodel.BaseViewModel
import com.loskon.features.wallpaperlist.domain.WallpaperListInteractor
import com.loskon.network.utlis.NetworkUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WallpaperListViewModel(
    private val wallpaperListInteractor: WallpaperListInteractor,
    private val networkUtil: NetworkUtil
) : BaseViewModel() {

    private val wallpaperListState = MutableStateFlow<WallpaperListState>(WallpaperListState.Loading)
    val getWallpaperListState get() = wallpaperListState.asStateFlow()

    private var job: Job? = null

    fun getWallpaperList(category: String) {
        job?.cancel()
        wallpaperListState.tryEmit(WallpaperListState.Loading)

        if (networkUtil.hasConnected().not()) {
            wallpaperListState.tryEmit(WallpaperListState.NoInternet)
        } else {
            job = launchErrorJob(
                errorBlock = { wallpaperListState.tryEmit(WallpaperListState.Failure) }
            ) {
                wallpaperListState.emit(WallpaperListState.Success(wallpaperListInteractor.getWallpapers(category)))
            }
        }
    }
}