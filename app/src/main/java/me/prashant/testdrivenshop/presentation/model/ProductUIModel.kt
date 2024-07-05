package me.prashant.testdrivenshop.presentation.model

// ProductUIModel.kt
data class ProductUIModel(
    val limit: Int,
    val products: List<ProductItemUIModel>,
    val skip: Int,
    val total: Int,
)

// ProductItemUIModel.kt
data class ProductItemUIModel(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val price: String = "",
    val discountPercentage: Double = 0.0,
    val rating: Double = 0.0,
    val stock: Int = 0,
    val tags: List<String> = emptyList(),
    val brand: String = "",
    val sku: String = "",
    val weight: Int = 0,
    val warrantyInformation: String = "",
    val shippingInformation: String = "",
    val availabilityStatus: String = "",
    val reviews: List<ReviewUIModel> = emptyList(),
    val returnPolicy: String = "",
    val minimumOrderQuantity: Int = 0,
    val images: List<String> = emptyList(),
    val thumbnail: String = ""
)


// DimensionsUIModel.kt
data class DimensionsUIModel(
    val width: Double,
    val height: Double,
    val depth: Double,
)

// ReviewUIModel.kt
data class ReviewUIModel(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
)

// MetaUIModel.kt
data class MetaUIModel(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String,
)
