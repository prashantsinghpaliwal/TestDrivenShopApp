package me.prashant.testdrivenshop.presentation.states

import me.prashant.testdrivenshop.presentation.model.CartItemUIModel

sealed class CartScreenViewState {
    data object Idle : CartScreenViewState()

    data class Loading(
        val isLoading: Boolean,
    ) : CartScreenViewState()

    data class Error(
        val error: String,
    ) : CartScreenViewState()

    data class CartLoadSuccess(
        val cartItems: List<CartItemUIModel>,
    ) : CartScreenViewState()

    data class CartItemCountSuccess(
        val count: Int,
    ) : CartScreenViewState()
}
