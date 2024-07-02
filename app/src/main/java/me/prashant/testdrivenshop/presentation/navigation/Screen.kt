package me.prashant.testdrivenshop.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel
import me.prashant.testdrivenshop.presentation.ui.screens.categories.CategoryScreen
import me.prashant.testdrivenshop.presentation.ui.screens.listing.ProductListingScreen

sealed class Screen(
    val route: String,
) {
    data object CategoryScreen : Screen("category_screen")

    data object ProductListingScreen :
        Screen("product_listing_screen/{selectedCategory}/{categoryList}") {
        fun createRoute(
            selectedCategory: String,
            categoryList: String,
        ) = "product_listing_screen/$selectedCategory/$categoryList"
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CategoryScreen.route,
    ) {
        composable(route = Screen.CategoryScreen.route) {
            CategoryScreen { selectedCategory, categoryList ->
                val selectedCategoryJson = Uri.encode(Gson().toJson(selectedCategory))
                val categoryListJson = Uri.encode(Gson().toJson(categoryList))
                navController.navigate(
                    Screen.ProductListingScreen.createRoute(
                        selectedCategoryJson,
                        categoryListJson,
                    ),
                )
            }
        }

        composable(
            route = Screen.ProductListingScreen.route,
            arguments =
                listOf(
                    navArgument("selectedCategory") { type = NavType.StringType },
                    navArgument("categoryList") { type = NavType.StringType },
                ),
        ) { backStackEntry ->
            val selectedCategoryJson = backStackEntry.arguments?.getString("selectedCategory")
            val categoryListJson = backStackEntry.arguments?.getString("categoryList")

            val selectedCategory: CategoryUIModel =
                Gson().fromJson(selectedCategoryJson, CategoryUIModel::class.java)
            val categoryList: List<CategoryUIModel> =
                Gson().fromJson(
                    categoryListJson,
                    object : TypeToken<List<CategoryUIModel>>() {}.type,
                )

            ProductListingScreen(selectedCategory = selectedCategory, categories = categoryList)
        }
    }
}
