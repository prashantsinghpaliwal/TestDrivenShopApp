package me.prashant.testdrivenshop.data.remote

// ProductDTO.kt
data class ProductDTO(
    val limit: Int,
    val products: List<ProductItemDTO>,
    val skip: Int,
    val total: Int,
)

// ProductItemDTO.kt
data class ProductItemDTO(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String,
    val sku: String,
    val weight: Int,
    val dimensions: DimensionsDTO,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<ReviewDTO>,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val meta: MetaDTO,
    val images: List<String>,
    val thumbnail: String,
)

// DimensionsDTO.kt
data class DimensionsDTO(
    val width: Double,
    val height: Double,
    val depth: Double,
)

// ReviewDTO.kt
data class ReviewDTO(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
)

// MetaDTO.kt
data class MetaDTO(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String,
)
