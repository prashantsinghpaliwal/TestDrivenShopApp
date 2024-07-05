package me.prashant.testdrivenshop.data.network

import me.prashant.testdrivenshop.data.remote.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductListingApiService {
    @GET("category/{category}")
    suspend fun getProducts(
        @Path("category") category: String,
    ): ProductDTO
}
