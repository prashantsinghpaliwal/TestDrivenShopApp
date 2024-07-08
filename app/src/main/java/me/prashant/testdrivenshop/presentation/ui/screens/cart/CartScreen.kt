package me.prashant.testdrivenshop.presentation.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.model.CartItemUIModel
import me.prashant.testdrivenshop.presentation.states.CartScreenViewState
import me.prashant.testdrivenshop.presentation.ui.screens.common.CustomToolbar

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getCartItems()
    }

    Scaffold(
        topBar = {
            CustomToolbar(
                leftIcon = R.drawable.arrow_left,
                title = "Cart",
                backgroundColor = Color.White,
                rightIcon = null,
                onLeftIconClick = { onBackClick.invoke() },
                onRightIconClick = { },
            )
        },
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            when (state) {
                is CartScreenViewState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is CartScreenViewState.CartLoadSuccess -> {
                    val cartItems = (state as CartScreenViewState.CartLoadSuccess).cartItems
                    if (cartItems.isEmpty()) {
                        Text(
                            text = "Your cart is empty",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier =
                                Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(16.dp),
                        )
                    } else {
                        CartProductListAndButton(cartItems = cartItems)
                    }
                }

                is CartScreenViewState.CartItemCountSuccess -> {
                    val cartItemCount = (state as CartScreenViewState.CartItemCountSuccess).count
                    Text(
                        text = "Cart Item Count: $cartItemCount",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier =
                            Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                    )
                }

                is CartScreenViewState.Error -> {
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier =
                            Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                    )
                }

                else -> {
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    cartItem: CartItemUIModel,
    onQuantityChange: (Boolean) -> Unit, // true for increase, false for decrease
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Product Image
            Image(
                painter = rememberImagePainter(cartItem.imageUrl),
                contentDescription = "Product Image",
                modifier =
                    Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Product Title and Price
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = cartItem.productName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = cartItem.price,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.Black,
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Quantity Selector
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Increase Quantity",
                    tint = Color.Black,
                )
                Text(
                    text = cartItem.quantity.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Decrease Quantity",
                    tint = Color.Black,
                )
            }
        }
    }
}
