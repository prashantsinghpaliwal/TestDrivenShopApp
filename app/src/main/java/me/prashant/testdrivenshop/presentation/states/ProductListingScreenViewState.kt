package me.prashant.testdrivenshop.presentation.states

import me.prashant.testdrivenshop.presentation.model.ProductUIModel

sealed class ProductListingScreenViewState {
    data object Idle : ProductListingScreenViewState()

    data class Loading(
        val isLoading: Boolean,
    ) : ProductListingScreenViewState()

    data class Success(
        val productUIModel: ProductUIModel,
    ) : ProductListingScreenViewState()

    data class Error(
        val message: String,
    ) : ProductListingScreenViewState()
}
