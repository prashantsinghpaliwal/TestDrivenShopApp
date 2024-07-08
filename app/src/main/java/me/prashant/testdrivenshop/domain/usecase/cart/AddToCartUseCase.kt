package me.prashant.testdrivenshop.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Inject

class AddToCartUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend operator fun invoke(cartItem: CartItemDomainModel): Flow<Boolean> {
           return cartRepository.addCartItem(cartItem)
        }
    }
