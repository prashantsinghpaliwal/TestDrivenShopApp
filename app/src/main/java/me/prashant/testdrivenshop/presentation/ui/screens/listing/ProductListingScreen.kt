package me.prashant.testdrivenshop.presentation.ui.screens.listing

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
    categories: List<CategoryUIModel>,
    viewModel: ProductViewModel = hiltViewModel(),
    onProductItemClick: (ProductItemUIModel) -> Unit,
) {
    val state by viewModel.state.collectAsState()
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
                onRightIconClick = { /* Handle right icon click */ },
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
            when (state) {
                is ProductListingScreenViewState.Loading -> {
                    val s = state as ProductListingScreenViewState.Loading
                    if (s.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                is ProductListingScreenViewState.Success -> {
                    val products =
                        (state as ProductListingScreenViewState.Success).productUIModel.products
                    ProductList(products = products) {
                        onProductItemClick.invoke(it)
                    }
                }

                is ProductListingScreenViewState.Error -> {
                    val message = (state as ProductListingScreenViewState.Error).message
                    Text(
                        text = message,
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
