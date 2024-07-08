package me.prashant.testdrivenshop.domain.usecase.productdetail

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.domain.repo.ProductRepository
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

class GetProductDetailUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        operator fun invoke(productId: String): Flow<Resource<ProductItem>> = productRepository.getProduct(productId)
    }
