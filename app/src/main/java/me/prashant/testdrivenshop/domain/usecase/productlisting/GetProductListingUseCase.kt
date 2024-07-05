package me.prashant.testdrivenshop.domain.usecase.productlisting

import me.prashant.testdrivenshop.domain.repo.ProductListingRepository
import javax.inject.Inject

class GetProductListingUseCase
    @Inject
    constructor(
        private val productRepository: ProductListingRepository,
    ) {
        suspend operator fun invoke(category: String) = productRepository.getProductListings(category)
    }
