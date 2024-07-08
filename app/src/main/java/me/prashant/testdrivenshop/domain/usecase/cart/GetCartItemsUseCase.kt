package me.prashant.testdrivenshop.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Inject

class GetCartItemsUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend operator fun invoke(): Flow<List<CartItemDomainModel>> = cartRepository.getCartItems()
    }
