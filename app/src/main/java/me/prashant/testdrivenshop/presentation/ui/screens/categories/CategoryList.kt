package me.prashant.testdrivenshop.presentation.ui.screens.categories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel

@Composable
fun CategoryList(
    categories: List<CategoryUIModel>,
    onCategoryClick: (CategoryUIModel) -> Unit,
) {
    LazyRow(
        modifier =
            Modifier
                .fillMaxWidth(),
        contentPadding = PaddingValues(all = 16.dp),
    ) {
        items(categories.chunked(2)) { rowItems ->
            Column {
                rowItems.forEach { category ->
                    Box(
                        modifier =
                            Modifier
                                .width(250.dp),
                    ) {
                        CategoryCard(
                            title = category.name,
                            subtitle = category.slug,
                            onClick = { onCategoryClick.invoke(category) },
                        )
                    }
                }
            }
        }
    }
}
