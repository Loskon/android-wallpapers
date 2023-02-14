package com.loskon.features.categorylist

import com.loskon.base.presentation.viewmodel.BaseViewModel
import com.loskon.features.model.CategoryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : BaseViewModel() {

    private val categoryListState = MutableStateFlow<List<CategoryModel>>(listOf())
    val getCategoryListState get() = categoryListState.asStateFlow()

    fun setCategoryMap(categories: List<CategoryModel>) {
        categoryListState.tryEmit(categories)
    }
}