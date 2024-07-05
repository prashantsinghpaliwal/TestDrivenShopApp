package me.prashant.testdrivenshop.presentation.ui.screens.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.prashant.testdrivenshop.domain.usecase.productlisting.GetProductListingUseCase
import me.prashant.testdrivenshop.presentation.mapper.ProductUIModelMapper
import me.prashant.testdrivenshop.presentation.model.ProductUIModel
import me.prashant.testdrivenshop.presentation.states.ProductListingScreenViewState
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

@HiltViewModel
class ProductViewModel
    @Inject
    constructor(
        private val useCase: GetProductListingUseCase,
        private val uiModelMapper: ProductUIModelMapper,
    ) : ViewModel() {
        private val _state =
            MutableStateFlow<ProductListingScreenViewState>(ProductListingScreenViewState.Idle)
        val state: StateFlow<ProductListingScreenViewState> = _state

        fun getProductListing(categoryId: String) {
            viewModelScope.launch {
                useCase.invoke(categoryId).collect {
                    when (it) {
                        is Resource.Loading -> {
                            _state.value = ProductListingScreenViewState.Loading(it.isLoading)
                        }

                        is Resource.Success -> {
                            _state.value =
                                ProductListingScreenViewState.Success(
                                    ProductUIModel(
                                        limit = it.data.limit,
                                        products =
                                            it.data.products.map { product ->
                                                uiModelMapper.convert(product)
                                            },
                                        skip = it.data.skip,
                                        total = it.data.total,
                                    ),
                                )
                        }

                        is Resource.Error -> {
                            _state.value =
                                ProductListingScreenViewState.Error(
                                    it.exception.message ?: "Something went wrong",
                                )
                        }
                    }
                }
            }
        }
    }
