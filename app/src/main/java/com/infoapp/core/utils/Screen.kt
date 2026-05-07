package com.infoapp.core.utils

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Dashboard : Screen("dashboard")

    // menuId = the parentId whose children to show; title = screen title
    data object MenuGrid : Screen("menu_grid/{menuId}/{title}") {
        fun createRoute(menuId: String, title: String) =
            "menu_grid/$menuId/${java.net.URLEncoder.encode(title, "UTF-8")}"
    }

    data object Detail : Screen("detail/{itemId}") {
        fun createRoute(itemId: String) = "detail/$itemId"
    }
}
