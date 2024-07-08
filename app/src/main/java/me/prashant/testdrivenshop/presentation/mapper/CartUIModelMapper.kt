package me.prashant.testdrivenshop.presentation.mapper

import me.prashant.testdrivenshop.domain.model.CartItemDomainModel
import me.prashant.testdrivenshop.presentation.model.CartItemUIModel
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class CartUIModelMapper
    @Inject
    constructor() : Mapper<CartItemDomainModel, CartItemUIModel> {
        override fun convert(input: CartItemDomainModel): CartItemUIModel =
            CartItemUIModel(
                id = input.id,
                productId = input.productId,
                productName = input.productName,
                price = input.price,
                quantity = input.quantity,
                imageUrl = input.imageUrl
            )
    }
