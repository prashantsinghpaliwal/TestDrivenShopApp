package me.prashant.testdrivenshop.presentation.ui.screens.listing

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel

@Composable
fun ProductListingScreen(
    selectedCategory: CategoryUIModel,
    categories: List<CategoryUIModel>,
    viewModel: ProductViewModel = hiltViewModel(),
) {
//    val state by viewModel.state.collectAsState()
//
//    // Fetch products based on selected category
//    LaunchedEffect(selectedCategory.slug) {
//        viewModel.getProducts(selectedCategory.slug)
//    }

    LaunchedEffect(Unit) {
        Log.e("ProductListingScreen", "selectedCategory: $selectedCategory," +
                "categories: $categories")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.White),
    ) {
        // Render category filter
//        Text(text = selectedCategory.name)
        Text(text = categories.first().name, color = Color.Red)
    }
}
