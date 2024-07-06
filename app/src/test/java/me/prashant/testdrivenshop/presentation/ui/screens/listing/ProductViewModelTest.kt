package me.prashant.testdrivenshop.presentation.ui.screens.listing

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.prashant.testdrivenshop.domain.model.Product
import me.prashant.testdrivenshop.domain.usecase.productlisting.GetProductListingUseCase
import me.prashant.testdrivenshop.presentation.mapper.ProductUIModelMapper
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel
import me.prashant.testdrivenshop.presentation.model.ProductUIModel
import me.prashant.testdrivenshop.presentation.states.ProductListingScreenViewState
import me.prashant.testdrivenshop.util.Resource
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductViewModelTest {
    private lateinit var viewModel: ProductViewModel
    private val useCase: GetProductListingUseCase = mockk()
    private val uiModelMapper: ProductUIModelMapper = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ProductViewModel(useCase, uiModelMapper)
    }

    @Test
    fun `getProductListing emits loading, success, and loading`() =
        runTest {
            val categoryId = "beauty"
            val product =
                Product(
                    limit = 10,
                    products = listOf(),
                    skip = 0,
                    total = 0,
                )
            val uiModel =
                ProductUIModel(
                    limit = product.limit,
                    products =
                        product.products.map {
                            ProductItemUIModel(
                                it.id,
                                it.title,
                                it.price,
                                it.description,
                                it.images.first(),
                            )
                        },
                    skip = product.skip,
                    total = product.total,
                )

            coEvery { useCase.invoke(categoryId) } returns
                flowOf(
                    Resource.Loading(true),
                    Resource.Success(product),
                    Resource.Loading(false),
                )
            coEvery { uiModelMapper.convert(any()) } returns ProductItemUIModel()

            viewModel.getProductListing(categoryId)

            viewModel.state.test {
                assertEquals(ProductListingScreenViewState.Loading(true), awaitItem())
                assertEquals(ProductListingScreenViewState.Success(uiModel), awaitItem())
                assertEquals(ProductListingScreenViewState.Loading(false), awaitItem())
            }
        }

    @Test
    fun `getProductListing emits loading, error, and loading`() =
        runTest {
            val categoryId = "beauty"
            val exception = Exception("Network error")

            coEvery { useCase.invoke(categoryId) } returns
                flowOf(
                    Resource.Loading(true),
                    Resource.Error(exception),
                    Resource.Loading(false),
                )

            viewModel.getProductListing(categoryId)

            viewModel.state.test {
                assertEquals(ProductListingScreenViewState.Loading(true), awaitItem())
                assertEquals(ProductListingScreenViewState.Error("Network error"), awaitItem())
                assertEquals(ProductListingScreenViewState.Loading(false), awaitItem())
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
