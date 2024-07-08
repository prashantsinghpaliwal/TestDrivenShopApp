package me.prashant.testdrivenshop.domain.usecase.cart

import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Inject

class AddToCartUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend operator fun invoke(cartItem: CartItemDomainModel) {
            cartRepository.addCartItem(cartItem)
        }
    }
