package me.prashant.testdrivenshop.data.network

import me.prashant.testdrivenshop.data.remote.CategoryItemDTO
import retrofit2.http.GET

interface CategoryApiService {
    @GET("categories")
    suspend fun getCategories(): List<CategoryItemDTO>
}
