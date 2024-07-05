package me.prashant.testdrivenshop.data.mapper

import me.prashant.testdrivenshop.data.remote.ProductItemDTO
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.domain.model.Review
import me.prashant.testdrivenshop.util.mapper.Mapper
import javax.inject.Inject

class ProductItemDTOToProductItemMapper
    @Inject
    constructor() : Mapper<ProductItemDTO, ProductItem> {
        override fun convert(input: ProductItemDTO): ProductItem =
            ProductItem(
                id = input.id,
                title = input.title,
                description = input.description,
                category = input.category,
                price = "$${input.price.toInt()}",
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
                        Review(
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
