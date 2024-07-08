package me.prashant.testdrivenshop.presentation.states

import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel

sealed class ProductDetailScreenViewState {
    data object Idle : ProductDetailScreenViewState()

    data object Loading : ProductDetailScreenViewState()

    data class Error(
        val error: String,
    ) : ProductDetailScreenViewState()

    data class Success(
        val product: ProductItemUIModel,
    ) : ProductDetailScreenViewState()
}
