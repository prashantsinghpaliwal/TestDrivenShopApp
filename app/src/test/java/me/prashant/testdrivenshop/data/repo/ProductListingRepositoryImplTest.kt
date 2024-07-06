package me.prashant.testdrivenshop.data.repo

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import me.prashant.testdrivenshop.data.mapper.ProductItemDTOToProductItemMapper
import me.prashant.testdrivenshop.data.network.ProductListingApiService
import me.prashant.testdrivenshop.data.remote.DimensionsDTO
import me.prashant.testdrivenshop.data.remote.MetaDTO
import me.prashant.testdrivenshop.data.remote.ProductDTO
import me.prashant.testdrivenshop.data.remote.ProductItemDTO
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.util.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductListingRepositoryImplTest {
    private val apiService: ProductListingApiService = mockk()
    private val productItemMapper: ProductItemDTOToProductItemMapper = mockk()
    private lateinit var repository: ProductListingRepositoryImpl

    @Before
    fun setUp() {
        repository = ProductListingRepositoryImpl(apiService, productItemMapper)
    }

    @Test
    fun `getProductListings emits loading, success and loading false`() =
        runTest {
            val category = "beauty"
            val productItemDTO = getMockProductItemDTO()
            val productItem = getMockProductItem()
            val productDTO = ProductDTO(10, listOf(productItemDTO), 0, 10)

            coEvery { apiService.getProducts(category) } returns productDTO
            coEvery { productItemMapper.convert(productItemDTO) } returns productItem

            repository.getProductListings(category).test {
                assertEquals(Resource.Loading(true), awaitItem())
                assertEquals(Resource.Success(Product(10, listOf(productItem), 0, 10)), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getProductListings emits error on network error`() =
        runTest {
            val category = "beauty"
            val exception = Exception("Network error")

            coEvery { apiService.getProducts(category) } throws exception

            repository.getProductListings(category).test {
                assertEquals(Resource.Loading(true), awaitItem())
                val emittedError = awaitItem()
                assert(emittedError is Resource.Error && emittedError.exception.message == "Network error")
                cancelAndIgnoreRemainingEvents()
            }
        }

    private fun getMockProductItemDTO(): ProductItemDTO =
        ProductItemDTO(
            id = 1,
            title = "Test Product",
            description = "Test Description",
            category = "beauty",
            price = 9.99,
            discountPercentage = 7.17,
            rating = 4.94,
            stock = 5,
            tags = listOf("beauty", "mascara"),
            brand = "Test Brand",
            sku = "RCH45Q1A",
            weight = 2,
            warrantyInformation = "1 month warranty",
            shippingInformation = "Ships in 1 month",
            availabilityStatus = "Low Stock",
            reviews = emptyList(),
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/image.png"),
            thumbnail = "https://example.com/thumbnail.png",
            dimensions = DimensionsDTO(0.0, 0.0, 0.0),
            meta = MetaDTO("", "", "", ""),
        )

    private fun getMockProductItem(): ProductItem =
        ProductItem(
            id = 1,
            title = "Test Product",
            description = "Test Description",
            category = "beauty",
            price = "9.99",
            discountPercentage = 7.17,
            rating = 4.94,
            stock = 5,
            tags = listOf("beauty", "mascara"),
            brand = "Test Brand",
            sku = "RCH45Q1A",
            weight = 2,
            warrantyInformation = "1 month warranty",
            shippingInformation = "Ships in 1 month",
            availabilityStatus = "Low Stock",
            reviews = emptyList(),
            returnPolicy = "30 days return policy",
            minimumOrderQuantity = 1,
            images = listOf("https://example.com/image.png"),
            thumbnail = "https://example.com/thumbnail.png",
        )
}
