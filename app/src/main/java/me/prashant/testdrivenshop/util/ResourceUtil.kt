package me.prashant.testdrivenshop.util

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

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