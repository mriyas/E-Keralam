package com.infoapp.core.network

import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.compose.*
import com.google.firebase.database.FirebaseDatabase
import com.infoapp.R
import com.infoapp.core.utils.Screen
import com.infoapp.domain.model.MenuItem
import com.infoapp.presentation.dashboard.MenuGridScreen
import com.infoapp.presentation.detail.DetailScreen
import com.infoapp.presentation.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // ── Splash ───────────────────────────────────────────────────────
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // ── Root Dashboard ───────────────────────────────────────────────
        composable(Screen.Dashboard.route) {
            MenuGridScreen(
                title = stringResource(R.string.app_name),
                isRoot = true,
                onMenuItemClick = { item -> handleMenuItemNavigation(navController, item) }
            )
        }

        // ── Drill-down sub-menu grid ─────────────────────────────────────
        composable(
            route = Screen.MenuGrid.route,
            arguments = listOf(
                navArgument("menuId") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Menu"
            MenuGridScreen(
                title = title,
                isRoot = false,
                onMenuItemClick = { item -> handleMenuItemNavigation(navController, item) },
                onBackClick = { navController.popBackStack() }
            )
        }

        // ── Detail / Content screen ──────────────────────────────────────
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("itemId") { type = NavType.StringType }
            )
        ) {
            DetailScreen(onBackClick = { navController.popBackStack() })
        }
    }
}

private fun handleMenuItemNavigation(navController: NavController, item: MenuItem) {
    if (item.hasSubMenu) {
        navController.navigate(Screen.MenuGrid.createRoute(item.id, item.name))
    } else {
        navController.navigate(Screen.Detail.createRoute(item.id))
    }
}
