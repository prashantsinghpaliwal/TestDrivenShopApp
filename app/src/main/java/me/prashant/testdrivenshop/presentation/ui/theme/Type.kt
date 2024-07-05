package me.prashant.testdrivenshop.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.prashant.testdrivenshop.R

val CustomFontFamily =
    FontFamily(
        Font(R.font.gilroy_regular, FontWeight.Normal),
        Font(R.font.gilroy_semi_bold, FontWeight.SemiBold),
        Font(R.font.gilroy_bold, FontWeight.Bold),
        Font(R.font.gilroy_medium, FontWeight.Medium),
        // Add other font weights if needed
    )

val Typography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 57.sp,
            ),
        displayMedium =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 45.sp,
            ),
        displaySmall =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
            ),
        headlineLarge =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
            ),
        headlineSmall =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
            ),
        titleLarge =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            ),
        titleSmall =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            ),
        labelMedium =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
            ),
        labelSmall =
            TextStyle(
                fontFamily = CustomFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
            ),
    )
