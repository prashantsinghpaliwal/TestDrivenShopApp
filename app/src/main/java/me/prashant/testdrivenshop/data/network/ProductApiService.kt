package me.prashant.testdrivenshop.data.network

import me.prashant.testdrivenshop.data.remote.ProductDTO
import me.prashant.testdrivenshop.data.remote.ProductItemDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("category/{category}")
    suspend fun getProducts(
        @Path("category") category: String,
    ): ProductDTO

    @GET("{product_id}")
    suspend fun getProduct(
        @Path("product_id") productId: String,
    ): ProductItemDTO
}
