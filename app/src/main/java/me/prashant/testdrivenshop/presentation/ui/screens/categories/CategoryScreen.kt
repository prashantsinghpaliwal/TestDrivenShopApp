package me.prashant.testdrivenshop.presentation.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.category.CategoryViewModel
import me.prashant.testdrivenshop.presentation.states.CategoryScreenViewState
import me.prashant.testdrivenshop.presentation.ui.screens.common.CustomToolbar
import me.prashant.testdrivenshop.presentation.ui.screens.common.SearchBar
import me.prashant.testdrivenshop.presentation.ui.theme.TestDrivenShopTheme

@Composable
fun CategoryScreen(viewModel: CategoryViewModel = hiltViewModel()) {
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
                onRightIconClick = { /* Handle right icon click */ },
            )
        },
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            var query by remember { mutableStateOf("") }

            SearchBar(
                query = query,
                onQueryChanged = { query = it },
                onSearchClicked = { /* Handle search clicked */ },
            )

            Spacer(modifier = Modifier.height(8.dp))

            when (state) {
                is CategoryScreenViewState.Loading -> {
                    // Show loading UI
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is CategoryScreenViewState.Success -> {
                    val categories = (state as CategoryScreenViewState.Success).categories

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(categories.size) { pos ->
                            CategoryCard(
                                title = categories[pos].name,
                                subtitle = categories[pos].slug,
                                onClick = { /* Handle category click */ },
                            )
                        }
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
