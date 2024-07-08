package me.prashant.testdrivenshop.presentation.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.prashant.testdrivenshop.domain.usecase.cart.CartUseCase
import me.prashant.testdrivenshop.presentation.mapper.CartUIModelMapper
import me.prashant.testdrivenshop.presentation.mapper.CartUIToDomainMapper
import me.prashant.testdrivenshop.presentation.model.CartItemUIModel
import me.prashant.testdrivenshop.presentation.states.CartScreenViewState
import javax.inject.Inject

@HiltViewModel
class CartViewModel
    @Inject
    constructor(
        private val cartUseCase: CartUseCase,
        private val mapper: CartUIModelMapper,
        private val uiToDomainMapper: CartUIToDomainMapper,
    ) : ViewModel() {
        private val _state = MutableStateFlow<CartScreenViewState>(CartScreenViewState.Idle)
        val state = _state.asStateFlow()

        fun getCartItems() {
            viewModelScope.launch {
                cartUseCase.getCartUseCase
                    .invoke()
                    .collect {
                        val result =
                            it.map {
                                mapper.convert(it)
                            }
                        _state.value = CartScreenViewState.CartLoadSuccess(result)
                    }
            }
        }

        fun getCartItemCount() {
            viewModelScope.launch {
                cartUseCase.getCartItemCountUseCase
                    .invoke()
                    .collect {
                        _state.value = CartScreenViewState.CartItemCountSuccess(it)
                    }
            }
        }

        fun addToCart(cartItem: CartItemUIModel) {
            viewModelScope.launch {
                val result = cartUseCase.addToCartUseCase.invoke(uiToDomainMapper.convert(cartItem))
                result.collect {
                    _state.value = CartScreenViewState.AddToCartSuccess(it)
                }
            }
        }

        fun removeCartItem(id: Int) {
            viewModelScope.launch {
                cartUseCase.removeFromCartUseCase.invoke(id)
            }
        }
    }
