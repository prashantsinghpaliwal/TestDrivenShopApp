package me.prashant.testdrivenshop.data.repo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.prashant.testdrivenshop.data.mapper.ProductItemDTOToProductItemMapper
import me.prashant.testdrivenshop.data.network.ProductListingApiService
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.repo.ProductListingRepository
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

class ProductListingRepositoryImpl
    @Inject
    constructor(
        private val apiService: ProductListingApiService,
        private val productItemMapper: ProductItemDTOToProductItemMapper,
    ) : ProductListingRepository {
        override fun getProductListings(category: String): Flow<Resource<Product>> =
            flow {
                emit(Resource.Loading(true)) // Initial loading state

                try {
                    val result = apiService.getProducts(category)
                    val product = Product(
                        limit = result.limit,
                        products = result.products.map { productItemMapper.convert(it) },
                        skip = result.skip,
                        total = result.total,
                    )

                    emit(Resource.Success(product)) // Success state
                } catch (e: Exception) {
                    emit(Resource.Error(Exception(e.localizedMessage ?: "An error occurred"))) // Error state
                }
            }
    }
