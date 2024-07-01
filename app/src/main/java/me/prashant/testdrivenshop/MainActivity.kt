package me.prashant.testdrivenshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.prashant.testdrivenshop.presentation.navigation.SetupNavGraph
import me.prashant.testdrivenshop.presentation.ui.theme.TestDrivenShopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestDrivenShopComposable()
        }
    }
}

@Composable
fun TestDrivenShopComposable() {
    val navController = rememberNavController()
    TestDrivenShopTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SetupNavGraph(navController = navController)
        }
    }
}
