package enriquez.mariajose.thecheezery.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import enriquez.mariajose.thecheezery.view.screens.CartScreen
import enriquez.mariajose.thecheezery.view.screens.LoginScreen
import enriquez.mariajose.thecheezery.view.screens.MenuScreen
import enriquez.mariajose.thecheezery.view.screens.ShowProductsScreen
import enriquez.mariajose.thecheezery.view.screens.WelcomeScreen
import enriquez.mariajose.thecheezery.viewModel.CartViewModel
import enriquez.mariajose.thecheezery.viewModel.LoginViewModel

@Composable
fun MyApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val onNavigateToLogin = { navController.navigate(Login) }
    val onNavigateToMenu = { navController.navigate(Menu) }
    val onNavigateToCart = { navController.navigate(Cart) }
    val cartViewModel: CartViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel()

    NavHost(navController = navController, startDestination = Welcome) {
        composable<Welcome> {
            WelcomeScreen(innerPadding, onNavigateToLogin)
        }

        composable<Login> {
            LoginScreen(innerPadding, onNavigateToMenu, loginViewModel)
        }
        composable<Menu> {
            MenuScreen(innerPadding, navController, onNavigateToCart)
        }
        composable(
            route = "show_products/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("category") ?: "Salties"

            ShowProductsScreen(
                category = categoryName,
                viewModel = cartViewModel,
                innerPadding = innerPadding
            )
        }

        composable<Cart> {
            CartScreen(
                viewModel = cartViewModel,
                innerPadding = innerPadding
            )
        }
    }
}