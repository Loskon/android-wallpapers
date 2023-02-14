package com.loskon.features.categorylist

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoryListModule = module {
    viewModel { CategoryViewModel() }
}