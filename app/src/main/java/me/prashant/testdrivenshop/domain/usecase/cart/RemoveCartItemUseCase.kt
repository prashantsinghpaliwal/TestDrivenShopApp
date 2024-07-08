package me.prashant.testdrivenshop.domain.usecase.cart

import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Inject

class RemoveCartItemUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend operator fun invoke(cartItemId: Int) {
            cartRepository.removeCartItem(cartItemId)
        }
    }
