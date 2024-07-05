package me.prashant.testdrivenshop.domain.repo

import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.util.Resource

interface ProductListingRepository {
    fun getProductListings(category: String): Flow<Resource<Product>>
}
