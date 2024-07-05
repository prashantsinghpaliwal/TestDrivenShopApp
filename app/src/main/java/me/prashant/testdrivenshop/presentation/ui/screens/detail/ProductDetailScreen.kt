package me.prashant.testdrivenshop.presentation.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.prashant.testdrivenshop.R
import me.prashant.testdrivenshop.presentation.ui.screens.common.CustomToolbar

@Composable
fun ProductDetailScreen() {
    Scaffold(
        topBar = {
            CustomToolbar(
                leftIcon = R.drawable.arrow_left,
                title = "",
                rightIcon = R.drawable.shopping_bag,
                onLeftIconClick = { /* Handle left icon click */ },
                onRightIconClick = { /* Handle right icon click */ },
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
        ) {
        }
    }
}
