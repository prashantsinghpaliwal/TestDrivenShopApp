package me.prashant.testdrivenshop.presentation.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.model.CartItemUIModel
import me.prashant.testdrivenshop.presentation.model.ProductItemUIModel
import me.prashant.testdrivenshop.presentation.states.ProductDetailScreenViewState
import me.prashant.testdrivenshop.presentation.ui.screens.cart.CartViewModel
import me.prashant.testdrivenshop.presentation.ui.screens.common.CustomToolbar
import me.prashant.testdrivenshop.util.getRandomColor

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    onCartClicked: () -> Unit,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getProductDetail(productId.toString())
    }

    val backgroundColor = getRandomColor()

    Scaffold(
        topBar = {
            CustomToolbar(
                leftIcon = R.drawable.arrow_left,
                title = "Product Detail",
                backgroundColor = backgroundColor,
                rightIcon = R.drawable.shopping_bag,
                onLeftIconClick = { onBackClick.invoke() },
                onRightIconClick = { onCartClicked.invoke() },
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
                is ProductDetailScreenViewState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Black,
                    )
                }

                is ProductDetailScreenViewState.Success -> {
                    val product = (state as ProductDetailScreenViewState.Success).product
                    ProductImage(backgroundColor, product)
                    ProductDetail(
                        product,
                        onAddToCartClick = {
                            cartViewModel.addToCart(
                                CartItemUIModel(
                                    productId = product.id,
                                    productName = product.title,
                                    quantity = 1,
                                    price = product.price,
                                    imageUrl = product.images.first()
                                ),
                            )
                        },
                    )
                }

                is ProductDetailScreenViewState.Error -> {
                    val message = (state as ProductDetailScreenViewState.Error).error
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

@Composable
fun ProductImage(
    backgroundColor: Color,
    product: ProductItemUIModel,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(backgroundColor),
    ) {
        Image(
            painter = rememberImagePainter(product.images.first()),
            contentDescription = "Product Image",
            modifier =
                Modifier
                    .width(160.dp)
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 12.dp,
                            bottomStart = 12.dp,
                        ),
                    ).align(Alignment.TopCenter),
            contentScale = ContentScale.Crop,
        )
    }
}
