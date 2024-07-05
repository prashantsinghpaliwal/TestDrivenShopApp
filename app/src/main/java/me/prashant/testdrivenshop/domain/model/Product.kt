package me.prashant.testdrivenshop.domain.model

// Product.kt
data class Product(
    val limit: Int,
    val products: List<ProductItem>,
    val skip: Int,
    val total: Int,
)

// ProductItem.kt
data class ProductItem(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: String,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String,
    val sku: String,
    val weight: Int,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Review>,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val images: List<String>,
    val thumbnail: String,
)

// Dimensions.kt
data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double,
)

// Review.kt
data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
)

// Meta.kt
data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String,
)
