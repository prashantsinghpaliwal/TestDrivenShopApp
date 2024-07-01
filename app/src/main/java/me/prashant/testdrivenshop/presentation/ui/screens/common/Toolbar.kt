package me.prashant.testdrivenshop.presentation.ui.screens.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    leftIcon: ImageVector,
    title: String,
    rightIcon: ImageVector,
    onLeftIconClick: () -> Unit,
    onRightIconClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onLeftIconClick) {
                Icon(imageVector = leftIcon, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = onRightIconClick) {
                Icon(imageVector = rightIcon, contentDescription = null)
            }
        },
    )
}
