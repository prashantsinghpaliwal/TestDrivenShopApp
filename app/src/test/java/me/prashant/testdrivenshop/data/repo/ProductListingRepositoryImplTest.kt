package me.prashant.testdrivenshop.data.repo

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import me.prashant.testdrivenshop.data.mapper.ProductItemDTOToProductItemMapper
import me.prashant.testdrivenshop.data.network.ProductListingApiService
import me.prashant.testdrivenshop.data.remote.ProductDTO
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.util.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductListingRepositoryImplTest {
    private lateinit var repository: ProductListingRepositoryImpl
    private val apiService: ProductListingApiService = mockk()
    private val productItemMapper: ProductItemDTOToProductItemMapper = mockk()

    @Before
    fun setUp() {
        repository = ProductListingRepositoryImpl(apiService, productItemMapper)
    }

    @Test
    fun `getProductListings emits loading, success, and loading`() =
        runTest {
            val category = "beauty"
            val productDTO = ProductDTO(limit = 5, products = listOf(mockk()), skip = 0, total = 10)
            val productItem = getMockProductItem()
            val product = Product(limit = 5, products = listOf(productItem), skip = 0, total = 10)

            coEvery { apiService.getProducts(category) } returns productDTO
            every { productItemMapper.convert(any()) } returns productItem

            val flow = repository.getProductListings(category)
            val emissions = flow.toList()

            assertEquals(2, emissions.size)
            assertEquals(Resource.Loading(true), emissions[0])
            assertEquals(Resource.Success(product), emissions[1])
        }

    @Test
    fun `getProductListings emits loading, error, and loading`() =
        runTest {
            val category = "beauty"
            val exception = Exception("Network error")

            coEvery { apiService.getProducts(category) } throws exception

            val flow = repository.getProductListings(category)
            val emissions = flow.toList()

            assertEquals(2, emissions.size)
            assertEquals(Resource.Loading(true), emissions[0])
            assert(emissions[1] is Resource.Error)
            assertEquals("Network error", (emissions[1] as Resource.Error).exception.message)
        }

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
