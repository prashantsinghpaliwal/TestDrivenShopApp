package me.prashant.testdrivenshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.prashant.testdrivenshop.data.mapper.CategoryDtoToCategoryMapper
import me.prashant.testdrivenshop.data.network.CategoryApiService
import me.prashant.testdrivenshop.data.repo.CategoryRepositoryImpl
import me.prashant.testdrivenshop.domain.repo.CategoryRepository
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCategoryApiService(): CategoryApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client =
            okhttp3.OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build()

        return Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/products/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryApiService: CategoryApiService,
        mapper: CategoryDtoToCategoryMapper,
    ): CategoryRepository = CategoryRepositoryImpl(categoryApiService, mapper)
}
