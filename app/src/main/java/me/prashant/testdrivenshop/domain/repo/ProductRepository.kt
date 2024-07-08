package me.prashant.testdrivenshop.domain.repo

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.util.Resource

interface ProductRepository {
    fun getProductListings(category: String): Flow<Resource<Product>>

    fun getProduct(productId: String): Flow<Resource<ProductItem>>
}
