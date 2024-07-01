package me.prashant.testdrivenshop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.prashant.testdrivenshop.presentation.ui.screens.categories.CategoryScreen

sealed class Screen(
    val route: String,
) {
    data object CategoryScreen : Screen("category_screen")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CategoryScreen.route,
    ) {
        composable(route = Screen.CategoryScreen.route) {
            CategoryScreen()
        }
    }
}
