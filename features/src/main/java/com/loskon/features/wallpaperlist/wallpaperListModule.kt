package com.loskon.features.wallpaperlist

import com.loskon.features.wallpaperlist.data.WallpaperListRepositoryImpl
import com.loskon.features.wallpaperlist.domain.WallpaperListInteractor
import com.loskon.features.wallpaperlist.domain.WallpaperListRepository
import com.loskon.features.wallpaperlist.presentation.WallpaperListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wallpaperListModule = module {
    single<WallpaperListRepository> { WallpaperListRepositoryImpl(get()) }
    factory { WallpaperListInteractor(get()) }
    viewModel { WallpaperListViewModel(get(), get()) }
}