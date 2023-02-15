package com.loskon.features.wallpaper

import com.loskon.features.wallpaper.data.WallpaperRepositoryImpl
import com.loskon.features.wallpaper.domain.WallpaperInteractor
import com.loskon.features.wallpaper.domain.WallpaperRepository
import com.loskon.features.wallpaper.presentation.WallpaperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wallpaperModule = module {
    single<WallpaperRepository> { WallpaperRepositoryImpl(get()) }
    factory { WallpaperInteractor(get()) }
    viewModel { WallpaperViewModel(get(), get()) }
}