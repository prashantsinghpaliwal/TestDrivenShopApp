package me.prashant.testdrivenshop.presentation.ui.screens.listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import me.prashant.testdrivenshop.presentation.ui.theme.Typography
import kotlin.random.Random

@Composable
fun ProductItem(
    title: String,
    price: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    val backgroundColor = getRandomColor()
    Column(
        modifier =
        Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = title,
            modifier =
            Modifier
                .aspectRatio(0.85f)
                .clip(RoundedCornerShape(12.dp))
                .background(backgroundColor),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = Typography.bodyLarge,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = price,
            style = Typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem(
        title = "Everyday Shirt",
        price = "$100",
        imageUrl = "",
    ) {
    }
}

val colorList =
    listOf(
        "#71C1DA",
        "#FDCBD6",
        "#FCC8D4",
        "#B5C9E6",
        "#F5C0CA"
    )

fun getRandomColor(): Color {
    val colorHex = colorList[Random.nextInt(colorList.size)]
    return Color(android.graphics.Color.parseColor(colorHex))
}
