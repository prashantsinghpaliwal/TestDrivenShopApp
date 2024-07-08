package me.prashant.testdrivenshop.domain.usecase.productlisting

import android.util.Log
import kotlinx.coroutines.flow.Flow
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.repo.ProductRepository
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

class GetProductListingUseCase @Inject constructor(
    private val productRepository: ProductRepository,
) {
    operator fun invoke(category: String): Flow<Resource<Product>> {
        val result = productRepository.getProductListings(category)
        Log.e("ProductListing", "UseCase $result")
        return result
    }
}
