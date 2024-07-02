package me.prashant.testdrivenshop.presentation.ui.screens.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.prashant.testdrivenshop.presentation.ui.theme.Typography

@Composable
fun CustomToolbar(
    @DrawableRes leftIcon: Int,
    title: String,
    @DrawableRes rightIcon: Int,
    onLeftIconClick: () -> Unit,
    onRightIconClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .padding(top = 24.dp, start = 12.dp, end = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onLeftIconClick) {
                Icon(
                    painter = painterResource(id = leftIcon),
                    contentDescription = "Left Icon"
                )
            }

            Text(
                text = title.uppercase(),
                style = Typography.titleLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
            )

            IconButton(onClick = onRightIconClick) {
                Icon(
                    painter = painterResource(id = rightIcon),
                    contentDescription = "Right Icon",
                )
            }
        }
    }
}

