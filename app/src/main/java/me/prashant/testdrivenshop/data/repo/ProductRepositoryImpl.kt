package me.prashant.testdrivenshop.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.prashant.testdrivenshop.data.mapper.ProductItemDTOToProductItemMapper
import me.prashant.testdrivenshop.data.network.ProductApiService
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.domain.repo.ProductRepository
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

class ProductRepositoryImpl
    @Inject
    constructor(
        private val apiService: ProductApiService,
        private val productItemMapper: ProductItemDTOToProductItemMapper,
    ) : ProductRepository {
        override fun getProductListings(category: String): Flow<Resource<Product>> =
            flow {
                emit(Resource.Loading(true)) // Initial loading state

                try {
                    val result = apiService.getProducts(category)
                    val product =
                        Product(
                            limit = result.limit,
                            products = result.products.map { productItemMapper.convert(it) },
                            skip = result.skip,
                            total = result.total,
                        )

                    emit(Resource.Success(product)) // Success state
                } catch (e: Exception) {
                    emit(
                        Resource.Error(
                            Exception(
                                e.localizedMessage ?: "An error occurred",
                            ),
                        ),
                    ) // Error state
                }
            }

        override fun getProduct(productId: String): Flow<Resource<ProductItem>> =
            flow {
                emit(Resource.Loading(true))

                try {
                    val result = apiService.getProduct(productId)
                    val productItem = productItemMapper.convert(result)
                    emit(Resource.Success(productItem))
                } catch (e: Exception) {
                    emit(Resource.Error(Exception(e.localizedMessage ?: "An error occurred")))
                }
            }
    }
