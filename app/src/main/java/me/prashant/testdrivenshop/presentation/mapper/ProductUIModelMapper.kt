package me.prashant.testdrivenshop.presentation.mapper

import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel
import me.prashant.testdrivenshop.presentation.model.ReviewUIModel
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class ProductUIModelMapper
    @Inject
    constructor() : Mapper<ProductItem, ProductItemUIModel> {
        override fun convert(input: ProductItem): ProductItemUIModel =
            ProductItemUIModel(
                id = input.id,
                title = input.title,
                description = input.description,
                category = input.category,
                price = input.price,
                discountPercentage = input.discountPercentage,
                rating = input.rating,
                stock = input.stock,
                tags = input.tags,
                brand = input.brand,
                sku = input.sku,
                weight = input.weight,
                warrantyInformation = input.warrantyInformation,
                shippingInformation = input.shippingInformation,
                availabilityStatus = input.availabilityStatus,
                reviews =
                    input.reviews.map {
                        ReviewUIModel(
                            rating = it.rating,
                            comment = it.comment,
                            date = it.date,
                            reviewerName = it.reviewerName,
                            reviewerEmail = it.reviewerEmail,
                        )
                    },
                returnPolicy = input.returnPolicy,
                minimumOrderQuantity = input.minimumOrderQuantity,
                images = input.images,
                thumbnail = input.thumbnail,
            )
    }
