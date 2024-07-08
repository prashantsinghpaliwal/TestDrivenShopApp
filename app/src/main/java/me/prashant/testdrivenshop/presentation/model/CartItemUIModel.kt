package me.prashant.testdrivenshop.presentation.model

data class CartItemUIModel(
    val id: Long = 0,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val price: String,
    val imageUrl: String,
)
