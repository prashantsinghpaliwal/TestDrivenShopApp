package me.prashant.testdrivenshop.data.mapper

import me.prashant.testdrivenshop.data.local.entities.CartItem
import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class CartItemEntityToDomainMapper
    @Inject
    constructor() : Mapper<CartItem, CartItemDomainModel> {
        override fun convert(input: CartItem): CartItemDomainModel =
            CartItemDomainModel(
                id = input.id,
                productId = input.productId,
                productName = input.productName,
                quantity = input.quantity,
                price = input.price,
                imageUrl = input.imageUrl
            )
    }
