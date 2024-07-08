package me.prashant.testdrivenshop.domain.usecase.cart

import javax.inject.Inject

data class CartUseCase
    @Inject
    constructor(
        val addToCartUseCase: AddToCartUseCase,
        val removeFromCartUseCase: RemoveCartItemUseCase,
        val getCartUseCase: GetCartItemsUseCase,
        val getCartItemCountUseCase: GetCartItemCountUseCase,
    )
