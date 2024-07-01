package me.prashant.testdrivenshop.presentation.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.prashant.testdrivenshop.domain.usecase.category.GetCategoriesUseCase
import me.prashant.testdrivenshop.presentation.mapper.CategoryUIModelMapper
import me.prashant.testdrivenshop.presentation.states.CategoryScreenViewState
import me.prashant.testdrivenshop.util.Resource
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
    @Inject
    constructor(
        private val useCase: GetCategoriesUseCase,
        private val uiModelMapper: CategoryUIModelMapper,
    ) : ViewModel() {
        private val _state = MutableStateFlow<CategoryScreenViewState>(CategoryScreenViewState.Idle)
        val state: StateFlow<CategoryScreenViewState> = _state

        fun getCategories() {
            _state.value = CategoryScreenViewState.Loading(true)

            viewModelScope.launch {
                useCase
                    .invoke()
                    .catch {
                        _state.value = CategoryScreenViewState.Error(it.message ?: "An error occurred")
                    }.collect {
                        when (it) {
                            is Resource.Loading -> {
                                _state.value = CategoryScreenViewState.Loading(it.isLoading)
                            }

                            is Resource.Success -> {
                                val uiModelList =
                                    it.data.map {
                                        uiModelMapper.convert(it)
                                    }

                                _state.value = CategoryScreenViewState.Success(uiModelList)
                            }

                            is Resource.Error -> {
                                _state.value =
                                    CategoryScreenViewState.Error(
                                        it.exception.message ?: "An error occurred",
                                    )
                            }
                        }
                    }
            }
        }
    }
