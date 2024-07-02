package me.prashant.testdrivenshop.presentation.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.prashant.testdrivenshop.R

@Composable
fun CategoryCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Card(
        modifier =
            Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 24.dp)
                .fillMaxWidth()
                .height(260.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color(0xFFFAC8D1),
            ),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
        ) {
            Box(
                modifier =
                Modifier
                    .size(100.dp)
                    .align(Alignment.TopStart)
                    .background(
                        color = Color(0xFF8e74d5),
                        shape =
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomEndPercent = 100,
                            bottomStartPercent = 0,
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "NEW '24",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
            }


            Column(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 16.dp, start = 16.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "SHOP NOW",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                )
            }

            Box(
                modifier =
                    Modifier
                        .size(80.dp)
                        .align(Alignment.BottomEnd)
                        .background(
                            color = Color(0xFF8e74d5),
                            shape =
                                RoundedCornerShape(
                                    topStartPercent = 100,
                                    topEndPercent = 0,
                                    bottomEndPercent = 0,
                                    bottomStartPercent = 0,
                                ),
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = "Left Icon",
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }
    }
}
