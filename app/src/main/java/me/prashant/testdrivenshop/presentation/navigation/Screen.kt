package me.prashant.testdrivenshop.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import me.prashant.testdrivenshop.presentation.model.CategoryUIModel
import me.prashant.testdrivenshop.presentation.ui.screens.cart.CartScreen
import me.prashant.testdrivenshop.presentation.ui.screens.categories.CategoryScreen
import me.prashant.testdrivenshop.presentation.ui.screens.detail.ProductDetailScreen
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

    data object ProductDetailScreen : Screen("productDetail/{productId}") {
        fun createRoute(productId: Int) = "productDetail/$productId"
    }

    data object CartScreen : Screen("cart")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val onCartClicked =
        object : () -> Unit {
            override fun invoke() {
                navController.navigate(Screen.CartScreen.route)
            }
        }

    NavHost(
        navController = navController,
        startDestination = Screen.CategoryScreen.route,
    ) {
        composable(route = Screen.CategoryScreen.route) {
//            CategoryScreen { selectedCategory, categoryList ->
//                val selectedCategoryJson = Uri.encode(Gson().toJson(selectedCategory))
//                val categoryListJson = Uri.encode(Gson().toJson(categoryList))
//                navController.navigate(
//                    Screen.ProductListingScreen.createRoute(
//                        selectedCategoryJson,
//                        categoryListJson,
//                    ),
//                )
//            }
            val categorySelected =
                object : (CategoryUIModel, List<CategoryUIModel>) -> Unit {
                    override fun invoke(
                        selectedCategory: CategoryUIModel,
                        categoryList: List<CategoryUIModel>,
                    ) {
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

            CategoryScreen(onCategorySelected = categorySelected, onCartClicked = onCartClicked)
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
            val selectedCategory: CategoryUIModel =
                Gson().fromJson(selectedCategoryJson, CategoryUIModel::class.java)

            ProductListingScreen(
                selectedCategory = selectedCategory,
                onCartClicked = onCartClicked,
            ) {
                navController.navigate(Screen.ProductDetailScreen.createRoute(it.id))
            }
        }

        composable(
            route = Screen.ProductDetailScreen.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
        ) {
            val productId = it.arguments?.getInt("productId")
            if (productId != null) {
                ProductDetailScreen(productId, onCartClicked = onCartClicked) {
                    navController.popBackStack()
                }
            }
        }

        composable(route = Screen.CartScreen.route) {
            CartScreen(
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
