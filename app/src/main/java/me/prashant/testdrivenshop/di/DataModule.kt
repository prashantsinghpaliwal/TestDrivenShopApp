package me.prashant.testdrivenshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.prashant.testdrivenshop.data.mapper.CategoryDtoToCategoryMapper
import me.prashant.testdrivenshop.data.mapper.ProductItemDTOToProductItemMapper
import me.prashant.testdrivenshop.data.network.CategoryApiService
import me.prashant.testdrivenshop.data.network.ProductListingApiService
import me.prashant.testdrivenshop.data.repo.CategoryRepositoryImpl
import me.prashant.testdrivenshop.data.repo.ProductListingRepositoryImpl
import me.prashant.testdrivenshop.domain.repo.CategoryRepository
import me.prashant.testdrivenshop.domain.repo.ProductListingRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/products/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiService = retrofit.create(CategoryApiService::class.java)

    @Provides
    @Singleton
    fun provideProductListingApiService(retrofit: Retrofit): ProductListingApiService =
        retrofit.create(ProductListingApiService::class.java)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        categoryApiService: CategoryApiService,
        mapper: CategoryDtoToCategoryMapper,
    ): CategoryRepository = CategoryRepositoryImpl(categoryApiService, mapper)

    @Provides
    @Singleton
    fun provideProductRepository(
        productListingApiService: ProductListingApiService,
        mapper: ProductItemDTOToProductItemMapper,
    ): ProductListingRepository = ProductListingRepositoryImpl(productListingApiService, mapper)
}
