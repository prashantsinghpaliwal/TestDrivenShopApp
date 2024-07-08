package me.prashant.testdrivenshop.presentation.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.prashant.testdrivenshop.domain.usecase.productdetail.GetProductDetailUseCase
import me.prashant.testdrivenshop.presentation.mapper.ProductUIModelMapper
import me.prashant.testdrivenshop.presentation.states.ProductDetailScreenViewState
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel
    @Inject
    constructor(
        private val mapper: ProductUIModelMapper,
        private val useCase: GetProductDetailUseCase,
    ) : ViewModel() {
        private val _state =
            MutableStateFlow<ProductDetailScreenViewState>(ProductDetailScreenViewState.Idle)
        val state = _state.asStateFlow()

        fun getProductDetail(productId: String) {
            viewModelScope.launch {
                useCase
                    .invoke(productId)
                    .collect {
                        when (it) {
                            is Resource.Loading -> {
                                _state.value = ProductDetailScreenViewState.Loading
                            }

                            is Resource.Success -> {
                                val uiModel = mapper.convert(it.data)
                                _state.value =
                                    ProductDetailScreenViewState.Success(uiModel)
                            }

                            is Resource.Error -> {
                                _state.value =
                                    ProductDetailScreenViewState.Error(
                                        it.exception.message ?: "Something went wrong",
                                    )
                            }
                        }
                    }
            }
        }
    }
