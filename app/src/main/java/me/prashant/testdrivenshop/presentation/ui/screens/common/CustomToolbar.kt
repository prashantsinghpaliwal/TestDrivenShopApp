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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.prashant.testdrivenshop.presentation.ui.theme.Typography

@Composable
fun CustomToolbar(
    modifier: Modifier = Modifier,
    @DrawableRes leftIcon: Int,
    backgroundColor: Color = Color.White,
    tintColor: Color = Color.Black,
    title: String,
    @DrawableRes rightIcon: Int?,
    onLeftIconClick: () -> Unit,
    onRightIconClick: () -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(backgroundColor)
                .padding(top = 24.dp, start = 12.dp, end = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = onLeftIconClick) {
                Icon(
                    painter = painterResource(id = leftIcon),
                    contentDescription = "Left Icon",
                    tint = tintColor,
                )
            }

            Text(
                text = title.uppercase(),
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                color = tintColor,
            )

            if (rightIcon != null) {
                IconButton(onClick = onRightIconClick) {
                    Icon(
                        painter = painterResource(id = rightIcon),
                        contentDescription = "Right Icon",
                        tint = tintColor,
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }
    }
}
