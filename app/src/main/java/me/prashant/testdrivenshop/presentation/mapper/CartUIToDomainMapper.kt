package me.prashant.testdrivenshop.presentation.mapper

import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.presentation.model.CartItemUIModel
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class CartUIToDomainMapper
    @Inject
    constructor() : Mapper<CartItemUIModel, CartItemDomainModel> {
        override fun convert(input: CartItemUIModel): CartItemDomainModel =
            CartItemDomainModel(
                id = input.id,
                productId = input.productId,
                productName = input.productName,
                price = input.price,
                quantity = input.quantity,
                imageUrl = input.imageUrl
            )
    }
