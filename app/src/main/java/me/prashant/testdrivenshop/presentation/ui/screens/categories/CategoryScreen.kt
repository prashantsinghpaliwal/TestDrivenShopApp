package me.prashant.testdrivenshop.presentation.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel
import me.prashant.testdrivenshop.presentation.states.CategoryScreenViewState
import me.prashant.testdrivenshop.presentation.ui.screens.common.CustomToolbar
import me.prashant.testdrivenshop.presentation.ui.screens.common.SearchBar

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onCategorySelected: (CategoryUIModel, List<CategoryUIModel>) -> Unit,
    onCartClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getCategories()
    }

    Scaffold(
        topBar = {
            CustomToolbar(
                leftIcon = R.drawable.ic_menu,
                title = "Shop",
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
            Column(
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                var query by remember { mutableStateOf("") }

                SearchBar(
                    query = query,
                    onQueryChanged = { query = it },
                    onSearchClicked = { /* Handle search clicked */ },
                )

                when (state) {
                    is CategoryScreenViewState.Loading -> {
                        // Show loading UI
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Black,
                        )
                    }

                    is CategoryScreenViewState.Success -> {
                        val categories = (state as CategoryScreenViewState.Success).categories
                        CategoryList(categories = categories) { selectedCategory ->
                            onCategorySelected(selectedCategory, categories)
                        }
                    }

                    is CategoryScreenViewState.Error -> {
                        val message = (state as CategoryScreenViewState.Error).error
                        Text(
                            text = message,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                        )
                    }

                    else -> {
                        // Show idle or initial UI
                    }
                }
            }
        }
    }
}
