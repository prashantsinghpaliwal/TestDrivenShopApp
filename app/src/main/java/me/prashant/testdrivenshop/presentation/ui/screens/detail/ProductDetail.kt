package me.prashant.testdrivenshop.presentation.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel
import me.prashant.testdrivenshop.presentation.states.CartScreenViewState

@Composable
fun ProductDetail(
    product: ProductItemUIModel,
    cartScreenViewState: CartScreenViewState,
    onAddToCartClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(onClick = { /* Handle wishlist click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.heart_filled),
                    tint = Color(0xFFED2951),
                    contentDescription = "Wishlist",
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.description,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp),
            color = Color(0xFF1C1C1C),
            lineHeight = 20.sp,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "${product.rating} ⭐ (${product.reviews.size} Reviews)",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            val isAlreadyAddedToCart: Boolean
            val buttonText: String

            when (cartScreenViewState) {
                is CartScreenViewState.AddToCartSuccess -> {
                    isAlreadyAddedToCart = true
                    buttonText = "Added to Cart"
                }

                else -> {
                    isAlreadyAddedToCart = false
                    buttonText = "Add to Cart"
                }
            }

            Button(
                onClick = {
                    if (!isAlreadyAddedToCart) {
                        onAddToCartClick()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp),
            ) {
                Text(
                    text = buttonText,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                    color = Color(0xFFFDFDFD),
                )
            }
        }
    }
}
