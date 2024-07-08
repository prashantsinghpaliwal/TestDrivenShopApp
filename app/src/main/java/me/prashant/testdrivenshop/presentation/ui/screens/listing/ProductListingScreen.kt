package me.prashant.testdrivenshop.presentation.ui.screens.listing

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel
import me.prashant.testdrivenshop.presentation.states.ProductListingScreenViewState
import me.prashant.testdrivenshop.presentation.ui.screens.common.CustomToolbar

@Composable
fun ProductListingScreen(
    selectedCategory: CategoryUIModel,
    viewModel: ProductListingViewModel = hiltViewModel(),
    onCartClicked: () -> Unit,
    onProductItemClick: (ProductItemUIModel) -> Unit,
) {
    val state by viewModel.state.collectAsState(initial = ProductListingScreenViewState.Loading(true))

    LaunchedEffect(Unit) {
        viewModel.getProductListing(selectedCategory.slug)
    }

    Scaffold(
        topBar = {
            CustomToolbar(
                leftIcon = R.drawable.arrow_left,
                title = selectedCategory.name,
                rightIcon = R.drawable.shopping_bag,
                onLeftIconClick = { /* Handle left icon click */ },
                onRightIconClick = { onCartClicked() },
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
        ) {
            Log.e("ProductListing", "ProductListingScreen $state")
            when (state) {
                is ProductListingScreenViewState.Loading -> {
                    val loadingState = state as ProductListingScreenViewState.Loading
                    if (loadingState.isLoading) {
                        CircularProgressIndicator(
                            modifier =
                                Modifier.align(
                                    Alignment.Center,
                                ),
                            color = Color.Black,
                        )
                    }
                }

                is ProductListingScreenViewState.Success -> {
                    val successState = state as ProductListingScreenViewState.Success
                    val products = successState.productUIModel.products
                    ProductList(products = products) {
                        onProductItemClick.invoke(it)
                    }
                }

                is ProductListingScreenViewState.Error -> {
                    val errorState = state as ProductListingScreenViewState.Error
                    Text(
                        text = errorState.message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }

                else -> {
                    // Show idle or initial UI
                }
            }
        }
    }
}
