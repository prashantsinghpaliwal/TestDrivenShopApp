package me.prashant.testdrivenshop.domain.usecase.cart

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Inject

class GetCartItemCountUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend operator fun invoke(): Flow<Int> = cartRepository.getCartItemCount()
    }
