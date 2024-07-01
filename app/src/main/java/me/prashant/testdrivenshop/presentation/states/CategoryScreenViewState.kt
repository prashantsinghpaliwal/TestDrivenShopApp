package me.prashant.testdrivenshop.presentation.states

import me.prashant.testdrivenshop.presentation.model.CategoryUIModel

sealed class CategoryScreenViewState {
    data object Idle : CategoryScreenViewState()

    data class Loading(
        val isLoading: Boolean,
    ) : CategoryScreenViewState()

    data class Error(
        val error: String,
    ) : CategoryScreenViewState()

    data class Success(
        val categories: List<CategoryUIModel>,
    ) : CategoryScreenViewState()
}
