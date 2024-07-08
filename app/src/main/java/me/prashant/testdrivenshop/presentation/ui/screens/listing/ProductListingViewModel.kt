package me.prashant.testdrivenshop.presentation.ui.screens.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.prashant.testdrivenshop.domain.usecase.productlisting.GetProductListingUseCase
import me.prashant.testdrivenshop.presentation.mapper.ProductUIModelMapper
import me.prashant.testdrivenshop.presentation.model.ProductUIModel
import me.prashant.testdrivenshop.presentation.states.ProductListingScreenViewState
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel
    @Inject
    constructor(
        private val useCase: GetProductListingUseCase,
        private val uiModelMapper: ProductUIModelMapper,
    ) : ViewModel() {
        private val _state = MutableSharedFlow<ProductListingScreenViewState>()
        val state: SharedFlow<ProductListingScreenViewState> = _state.asSharedFlow()

        fun getProductListing(categoryId: String) {
            viewModelScope.launch {
                useCase.invoke(categoryId).collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _state.emit(ProductListingScreenViewState.Loading(resource.isLoading))
                        }

                        is Resource.Success -> {
                            _state.emit(
                                ProductListingScreenViewState.Success(
                                    ProductUIModel(
                                        limit = resource.data.limit,
                                        products =
                                            resource.data.products.map { product ->
                                                uiModelMapper.convert(product)
                                            },
                                        skip = resource.data.skip,
                                        total = resource.data.total,
                                    ),
                                ),
                            )
                        }

                        is Resource.Error -> {
                            _state.emit(
                                ProductListingScreenViewState.Error(
                                    resource.exception.message ?: "Something went wrong",
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
