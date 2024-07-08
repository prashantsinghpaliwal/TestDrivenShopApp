package me.prashant.testdrivenshop.domain.model

data class CartItemDomainModel(
    val id: Long = 0,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val price: String,
    val imageUrl: String
)
