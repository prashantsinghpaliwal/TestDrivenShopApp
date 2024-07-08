package me.prashant.testdrivenshop.data.mapper

import me.prashant.testdrivenshop.data.local.entities.CartItem
import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class CartItemDomainToEntityMapper
    @Inject
    constructor() : Mapper<CartItemDomainModel, CartItem> {
        override fun convert(input: CartItemDomainModel): CartItem =
            CartItem(
                id = input.id,
                productId = input.productId,
                productName = input.productName,
                quantity = input.quantity,
                price = input.price,
                imageUrl = input.imageUrl
            )
    }
