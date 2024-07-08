package me.prashant.testdrivenshop.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.prashant.testdrivenshop.data.local.room.CartDao
import me.prashant.testdrivenshop.data.mapper.CartItemDomainToEntityMapper
import me.prashant.testdrivenshop.data.mapper.CartItemEntityToDomainMapper
import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Inject

class CartRepositoryImpl
    @Inject
    constructor(
        private val cartDao: CartDao,
        private val cartItemDomainToEntityMapper: CartItemDomainToEntityMapper,
        private val cartItemEntityToDomainMapper: CartItemEntityToDomainMapper,
    ) : CartRepository {
        override suspend fun addCartItem(cartItem: CartItemDomainModel): Flow<Boolean> =
            flow {
                cartDao.insertCartItem(cartItemDomainToEntityMapper.convert(cartItem))
                emit(true)
            }

        override suspend fun getCartItems(): Flow<List<CartItemDomainModel>> {
            val result =
                cartDao.getCartItems().map {
                    it.map { cartItem ->
                        cartItemEntityToDomainMapper.convert(cartItem)
                    }
                }
            return result
        }

        override fun getCartItemCount(): Flow<Int> = cartDao.getCartItemCount()

        override suspend fun removeCartItem(id: Int) {
            cartDao.deleteCartItem(id)
        }
    }
