package me.prashant.testdrivenshop.domain.repo

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.CartItemDomainModel

interface CartRepository {
    suspend fun addCartItem(cartItem: CartItemDomainModel): Flow<Boolean>

    suspend fun getCartItems(): Flow<List<CartItemDomainModel>>

    fun getCartItemCount(): Flow<Int>

    suspend fun removeCartItem(id: Int)
}
