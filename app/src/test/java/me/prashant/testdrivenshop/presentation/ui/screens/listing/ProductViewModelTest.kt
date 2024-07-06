package me.prashant.testdrivenshop.presentation.ui.screens.listing

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.model.ProductItem
import me.prashant.testdrivenshop.domain.usecase.productlisting.GetProductListingUseCase
import me.prashant.testdrivenshop.presentation.mapper.ProductUIModelMapper
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel
import me.prashant.testdrivenshop.presentation.model.ProductUIModel
import me.prashant.testdrivenshop.presentation.states.ProductListingScreenViewState
import me.prashant.testdrivenshop.util.Resource
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@Deprecated("Use ProductViewModelTest")
@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest2 {
    private val useCase: GetProductListingUseCase = mockk()
    private val uiModelMapper: ProductUIModelMapper = mockk()
    private lateinit var viewModel: ProductViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductViewModel(useCase, uiModelMapper)
    }

    @Test
    fun `getProductListing emits loading and success`() =
        runTest {
            val categoryId = "beauty"

            val product = Product(10, listOf(getMockProductItem()), 0, 10)
            val productUIModel = ProductUIModel(10, listOf(getMockProductItemUIModel()), 0, 10)

            coEvery { useCase.invoke(categoryId) } returns
                flowOf(
                    Resource.Success(product),
                )
            coEvery { uiModelMapper.convert(any()) } returns getMockProductItemUIModel()

            viewModel.getProductListing(categoryId)

            viewModel.state.test {
                assertEquals(ProductListingScreenViewState.Success(productUIModel), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getProductListing emits error on exception1`() =
        runTest {
            val categoryId = "beauty"
            val exception = Exception("Something went wrong")

            coEvery { useCase.invoke(categoryId) } returns
                flowOf(
                    Resource.Loading(true),
                    Resource.Error(exception),
//            Resource.Loading(false)
                )

            // Create a new instance of the ViewModel for each test to ensure a clean slate
            val viewModel = ProductViewModel(useCase, uiModelMapper)

            viewModel.getProductListing(categoryId)

            viewModel.state.test {
                assertEquals(ProductListingScreenViewState.Loading(true), awaitItem())
//            assertEquals(ProductListingScreenViewState.Error("Something went wrong"), awaitItem())
//            assertEquals(ProductListingScreenViewState.Loading(false), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
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

    private fun getMockProductItemUIModel(): ProductItemUIModel =
        ProductItemUIModel(
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
