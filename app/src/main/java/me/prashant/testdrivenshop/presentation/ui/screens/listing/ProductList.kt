package me.prashant.testdrivenshop.presentation.ui.screens.listing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel

@Composable
fun ProductList(
    products: List<ProductItemUIModel>,
    onProductItemClick: (ProductItemUIModel) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(products) {
            ProductItem(
                title = it.title,
                price = it.price,
                imageUrl = it.images.first(),
                onClick = {
                    onProductItemClick.invoke(it)
                },
            )
        }
    }
}
